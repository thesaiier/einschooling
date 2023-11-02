package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import jakarta.annotation.security.PermitAll;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student-five", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class EnrollStudentViewSectionFive extends Div{

    public EnrollStudentViewSectionFive(){
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
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, TextColor.SECONDARY);
        ProgressBar progressBar = new ProgressBar(1, 6, 5);
        progressBar.addClassNames(Margin.Top.XLARGE);
        Paragraph progressParagraph = new Paragraph("Einschulungsfortschritt");
        progressParagraph.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.TERTIARY);
        checkoutForm.add(progressBar, progressParagraph, header, note);

        checkoutForm.add(new Hr());
        checkoutForm.add(apprenticeshipDetails());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }
    private Section apprenticeshipDetails() {
        Section apprenticeshipDetails = new Section();
        apprenticeshipDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepFive = new Paragraph("Schritt 5/6");
        stepFive.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Gesetzlicher Vertreter");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        HorizontalLayout familyNameLayout = new HorizontalLayout();
        familyNameLayout.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);


        TextField familyName = new TextField("Familienname");
        familyName.setRequiredIndicatorVisible(true);
        familyName.addClassNames(Margin.Bottom.SMALL);

        TextField familyFirstname = new TextField("Vorname");
        familyFirstname.setRequiredIndicatorVisible(true);
        familyFirstname.addClassNames(Margin.Bottom.SMALL);

        familyNameLayout.add(familyName, familyFirstname);

        HorizontalLayout adressLayout = new HorizontalLayout();
        adressLayout.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField familyAdress = new TextField("PLZ Wohnort");
        familyAdress.setRequiredIndicatorVisible(true);
        familyAdress.addClassNames(Margin.Bottom.SMALL);

        TextField familyStreet = new TextField("Straße");
        familyStreet.setRequiredIndicatorVisible(true);
        familyStreet.addClassNames(Margin.Bottom.SMALL);

        adressLayout.add(familyAdress, familyStreet);

        HorizontalLayout contactLayout = new HorizontalLayout();
        contactLayout.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField familyPhonePrivate = new TextField("Telefon (privat)");
        familyPhonePrivate.addClassNames(Margin.Bottom.SMALL);
        familyPhonePrivate.setRequiredIndicatorVisible(true);

        TextField familyPhoneBusiness = new TextField("Telefon (dienstlich)");
        familyPhoneBusiness.addClassName(Margin.Bottom.SMALL);
        familyPhoneBusiness.setRequiredIndicatorVisible(true);

        contactLayout.add(familyPhonePrivate, familyPhoneBusiness);



        apprenticeshipDetails.add(stepFive, header, familyNameLayout, adressLayout, contactLayout);
        return apprenticeshipDetails;
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

        Button back = Buttons.createPrimaryButton("Zurück", "left", "enroll-student-three");

        Button send = Buttons.createPrimaryButton("Weiter", "right", "certificats");

        twoButtons.add(back, send);

        buttonLayout.add(cancel, twoButtons);
        footer.add(buttonLayout);
        return footer;
    }
}
