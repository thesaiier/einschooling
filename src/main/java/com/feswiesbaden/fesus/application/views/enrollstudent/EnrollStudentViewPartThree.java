package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import jakarta.annotation.security.PermitAll;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student-three", layout = MainLayout.class)
@PermitAll
public class EnrollStudentViewPartThree extends Div{

    public EnrollStudentViewPartThree(){
        addClassNames("enroll-student-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.FLEX, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Left.XLARGE, Margin.Right.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);
        //content.addClassNames(Display.GRID, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
        //        Margin.Horizontal.LARGE, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createSchoolingForm());
        //content.add(createAside());
        add(content);
    }

    private Component createSchoolingForm() {

        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, LumoUtility.Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, LumoUtility.FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, LumoUtility.TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(new Hr());
        checkoutForm.add(createSchoolDetailsSection());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }
    private Section createSchoolDetailsSection() {
        Section previousSchoolDetails = new Section();
        previousSchoolDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Schritt 3/3");
        stepTwo.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        H3 header = new H3("Schuldetails");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE);

        MultiSelectComboBox<String> previousGraduation = new MultiSelectComboBox<>("Bereits abgeschlossene Schule");
        previousGraduation.setItems("Grundschule", "Hauptschule", "Realschule", "Fachoberstufe", "Gymnasiale Oberstufe");
        previousGraduation.setRequiredIndicatorVisible(true);
        previousGraduation.addClassNames(Margin.Bottom.SMALL);

        return previousSchoolDetails;
    }
    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Schließen");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button send = new Button("Senden", new Icon(VaadinIcon.ARROW_RIGHT));
        send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        send.setIconAfterText(true);

        Anchor weiter = new Anchor("enroll-student-three", "part3");


        footer.add(cancel, send, weiter);
        return footer;
    }
}
