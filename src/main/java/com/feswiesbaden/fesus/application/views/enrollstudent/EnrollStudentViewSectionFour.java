package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
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
@Route(value = "enroll-student-four", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class EnrollStudentViewSectionFour extends Div{

    public EnrollStudentViewSectionFour(){
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
        ProgressBar progressBar = new ProgressBar(1, 6, 4);
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

        Paragraph stepFive = new Paragraph("Schritt 4/6");
        stepFive.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        H3 header = new H3("Ausbildungsbetrieb");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE);

        TextField firmName = new TextField("Firma");
        firmName.setRequiredIndicatorVisible(true);
        firmName.addClassNames(Margin.Bottom.SMALL);

        Div adressDiv = new Div();
        adressDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField firmAddress = new TextField("PLZ Ort");
        firmAddress.setRequiredIndicatorVisible(true);
        firmAddress.addClassNames(Margin.Bottom.SMALL);

        TextField firmStreet = new TextField("Straße");
        firmStreet.setRequiredIndicatorVisible(true);
        firmStreet.addClassNames(Margin.Bottom.SMALL);

        adressDiv.add(firmAddress, firmStreet);

        Div contactDiv = new Div();
        contactDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField firmPhoneNumber = new TextField("Telefon");
        firmPhoneNumber.addClassNames(Margin.Bottom.SMALL);
        firmPhoneNumber.setRequiredIndicatorVisible(true);

        TextField firmFax = new TextField("Fax");
        firmFax.addClassName(Margin.Bottom.SMALL);
        firmFax.setRequiredIndicatorVisible(true);

        contactDiv.add(firmPhoneNumber, firmFax);

        Div infoDiv = new Div();
        infoDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        DatePicker dateSinceBeginning = new DatePicker("Beginn der Ausbildung");
        dateSinceBeginning.setRequiredIndicatorVisible(true);
        dateSinceBeginning.addClassNames(Margin.Bottom.SMALL);

        TextField firmEmail = new TextField("E-Mail");
        firmEmail.setRequiredIndicatorVisible(true);
        firmEmail.addClassNames(Margin.Bottom.SMALL);

        infoDiv.add(dateSinceBeginning, firmEmail);

        apprenticeshipDetails.add(stepFive, header, firmName, adressDiv, contactDiv, infoDiv);
        return apprenticeshipDetails;
    }
    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassNames(LumoUtility.Gap.MEDIUM);
        buttonLayout.setWidth("100%");

        HorizontalLayout twoButtons = new HorizontalLayout();
        twoButtons.addClassNames(Display.FLEX, LumoUtility.FlexWrap.WRAP, LumoUtility.Gap.MEDIUM, Margin.Left.AUTO);


        Button cancel = Buttons.createSecondaryButton("Abbruch", "about");
        Button send = Buttons.createPrimaryButton("Weiter", "right", "enroll-student-five");
        Button back = Buttons.createPrimaryButton("Weiter", "left", "enroll-student-three");

        twoButtons.add(back, send);

        buttonLayout.add(cancel, twoButtons);
        footer.add(buttonLayout);
        return footer;
    }
}
