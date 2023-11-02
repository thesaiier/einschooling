package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import jakarta.annotation.security.PermitAll;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student-three", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class EnrollStudentViewSectionThree extends Div{

    public EnrollStudentViewSectionThree(){
        addClassNames("enroll-student-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.FLEX, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Left.XLARGE, Margin.Right.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createSchoolingForm());
        add(content);
    }

    private Component createSchoolingForm() {

        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, LumoUtility.Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, LumoUtility.FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, LumoUtility.TextColor.SECONDARY);
        ProgressBar progressBar = new ProgressBar(1, 6, 3);
        progressBar.addClassNames(Margin.Top.XLARGE);
        Paragraph progressParagraph = new Paragraph("Einschulungsfortschritt");
        progressParagraph.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.TERTIARY);
        checkoutForm.add(progressBar, progressParagraph, header, note);

        checkoutForm.add(new Hr());
        checkoutForm.add(createSchoolDetailsSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }
    private Section createSchoolDetailsSection() {
        Section previousSchoolDetails = new Section();
        previousSchoolDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Schritt 3/6");
        stepThree.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        H3 header = new H3("Vorherige Schuldetails");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE);

        TextField previousSchoolName = new TextField("Name der zuletzt besuchten Schule");
        previousSchoolName.setRequiredIndicatorVisible(true);
        previousSchoolName.addClassNames(Margin.Bottom.SMALL);

        Div schoolDiv = new Div();
        schoolDiv.addClassNames(Display.FLEX, LumoUtility.FlexWrap.WRAP, LumoUtility.Gap.MEDIUM);

        TextField previousSchoolAddress = new TextField("Straße");
        previousSchoolAddress.setRequiredIndicatorVisible(true);
        previousSchoolAddress.addClassNames(Margin.Bottom.SMALL);

        TextField previousSchoolPostal = new TextField("PLZ und Ort der zuletzt besuchten Schule");
        previousSchoolPostal.setRequiredIndicatorVisible(true);
        previousSchoolPostal.addClassNames(Margin.Bottom.SMALL);

        schoolDiv.add(previousSchoolPostal, previousSchoolAddress);

        ComboBox<String> previousSchoolType = new ComboBox<>("Zuletzt besuchte Schulform");
        previousSchoolType.setItems("Grundschule", "Hauptschule", "Realschule", "Fachoberstufe", "Gymnasiale Oberstufe", "Berufsschule");
        previousSchoolType.setRequiredIndicatorVisible(true);
        previousSchoolType.addClassNames(Margin.Bottom.SMALL);

        ComboBox<String> highestGraduation = new ComboBox<>("Höchster erreichter Abschluss");
        highestGraduation.setItems("Hauptschulabschluss", "Realschulabschluss", "Fachhochschulreife", "Hochschulreife (Abitur)");
        highestGraduation.setRequiredIndicatorVisible(true);
        highestGraduation.addClassNames(Margin.Bottom.SMALL);


        previousSchoolDetails.add(stepThree, header, previousSchoolName, schoolDiv, previousSchoolType, highestGraduation);
        return previousSchoolDetails;
    }
    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassNames(LumoUtility.Gap.MEDIUM);
        buttonLayout.setWidth("100%");

        Button cancel = Buttons.createSecondaryButton("Abbruch", "about");

        HorizontalLayout twoButtons = new HorizontalLayout();
        twoButtons.addClassNames(Display.FLEX, LumoUtility.FlexWrap.WRAP, LumoUtility.Gap.MEDIUM, Margin.Left.AUTO);


        Button send = Buttons.createPrimaryButton("Weiter", "right", "enroll-student-four");
        Button back = Buttons.createPrimaryButton("Weiter", "left", "enroll-student-two");

        twoButtons.add(back, send);

        buttonLayout.add(cancel, twoButtons);
        footer.add(buttonLayout);
        return footer;
    }
}
