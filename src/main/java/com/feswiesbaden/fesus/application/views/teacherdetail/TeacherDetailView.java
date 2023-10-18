package com.feswiesbaden.fesus.application.views.teacherdetail;

import com.feswiesbaden.fesus.application.data.entity.Teacher;
import com.feswiesbaden.fesus.application.data.service.TeacherService;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.RolesAllowed;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Teacher Detail")
@Route(value = "teacher-Detail/:teacherID?/:action?(edit)", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class TeacherDetailView extends Div implements BeforeEnterObserver {

    private final String TEACHER_ID = "teacherID";
    private final String TEACHER_EDIT_ROUTE_TEMPLATE = "teacher-Detail/%s/edit";

    private final Grid<Teacher> grid = new Grid<>(Teacher.class, false);

    private Upload avatar;
    private Image avatarPreview;
    private TextField teacherId;
    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField role;
    private Checkbox important;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<Teacher> binder;

    private Teacher teacher;

    private final TeacherService teacherService;

    public TeacherDetailView(TeacherService teacherService) {
        this.teacherService = teacherService;
        addClassNames("teacher-detail-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        LitRenderer<Teacher> avatarRenderer = LitRenderer.<Teacher>of(
                "<span style='border-radius: 50%; overflow: hidden; display: flex; align-items: center; justify-content: center; width: 64px; height: 64px'><img style='max-width: 100%' src=${item.avatar} /></span>")
                .withProperty("avatar", item -> {
                    if (item != null && item.getAvatar() != null) {
                        return "data:image;base64," + Base64.getEncoder().encodeToString(item.getAvatar());
                    } else {
                        return "";
                    }
                });
        grid.addColumn(avatarRenderer).setHeader("Avatar").setWidth("96px").setFlexGrow(0);

        grid.addColumn("teacherId").setAutoWidth(true);
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("role").setAutoWidth(true);
        LitRenderer<Teacher> importantRenderer = LitRenderer.<Teacher>of(
                "<vaadin-icon icon='vaadin:${item.icon}' style='width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s); color: ${item.color};'></vaadin-icon>")
                .withProperty("icon", important -> important.isImportant() ? "check" : "minus").withProperty("color",
                        important -> important.isImportant()
                                ? "var(--lumo-primary-text-color)"
                                : "var(--lumo-disabled-text-color)");

        grid.addColumn(importantRenderer).setHeader("Important").setAutoWidth(true);

        grid.setItems(query -> teacherService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(TEACHER_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(TeacherDetailView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Teacher.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        attachImageUpload(avatar, avatarPreview);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.teacher == null) {
                    this.teacher = new Teacher();
                }
                binder.writeBean(this.teacher);
                teacherService.update(this.teacher);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(TeacherDetailView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> teacherId = event.getRouteParameters().get(TEACHER_ID).map(Long::parseLong);
        if (teacherId.isPresent()) {
            Optional<Teacher> teacherFromBackend = teacherService.get(teacherId.get());
            if (teacherFromBackend.isPresent()) {
                populateForm(teacherFromBackend.get());
            } else {
                Notification.show(String.format("The requested teacher was not found, ID = %s", teacherId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(TeacherDetailView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        Label avatarLabel = new Label("Avatar");
        avatarPreview = new Image();
        avatarPreview.setWidth("100%");
        avatar = new Upload();
        avatar.getStyle().set("box-sizing", "border-box");
        avatar.getElement().appendChild(avatarPreview.getElement());
        teacherId = new TextField("Teacher Id");
        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        email = new TextField("Email");
        role = new TextField("Role");
        important = new Checkbox("Important");
        formLayout.add(avatarLabel, avatar, teacherId, firstName, lastName, email, role, important);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void attachImageUpload(Upload upload, Image preview) {
        ByteArrayOutputStream uploadBuffer = new ByteArrayOutputStream();
        upload.setAcceptedFileTypes("image/*");
        upload.setReceiver((fileName, mimeType) -> {
            uploadBuffer.reset();
            return uploadBuffer;
        });
        upload.addSucceededListener(e -> {
            StreamResource resource = new StreamResource(e.getFileName(),
                    () -> new ByteArrayInputStream(uploadBuffer.toByteArray()));
            preview.setSrc(resource);
            preview.setVisible(true);
            if (this.teacher == null) {
                this.teacher = new Teacher();
            }
            this.teacher.setAvatar(uploadBuffer.toByteArray());
        });
        preview.setVisible(false);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Teacher value) {
        this.teacher = value;
        binder.readBean(this.teacher);
        this.avatarPreview.setVisible(value != null);
        if (value == null || value.getAvatar() == null) {
            this.avatar.clearFileList();
            this.avatarPreview.setSrc("");
        } else {
            this.avatarPreview.setSrc("data:image;base64," + Base64.getEncoder().encodeToString(value.getAvatar()));
        }

    }
}
