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
@Route(value = "enroll-student-two", layout = MainLayout.class)
@PermitAll
public class EnrollStudentViewPartTwo extends Div{

    public EnrollStudentViewPartTwo(){
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
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }
    private Section createSchoolDetailsSection() {
        Section previousSchoolDetails = new Section();
        previousSchoolDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Schritt 2/3");
        stepTwo.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        H3 header = new H3("Schuldetails");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE);

        MultiSelectComboBox<String> previousGraduation = new MultiSelectComboBox<>("Bereits abgeschlossene Schule");
        previousGraduation.setItems("Grundschule", "Hauptschule", "Realschule", "Fachoberstufe", "Gymnasiale Oberstufe");
        previousGraduation.setRequiredIndicatorVisible(true);
        previousGraduation.addClassNames(Margin.Bottom.SMALL);
        /*

        ComboBox<String> countrySelect = new ComboBox<>("Country");
        countrySelect.setRequiredIndicatorVisible(true);
        countrySelect.addClassNames(Margin.Bottom.SMALL);

        TextArea address = new TextArea("Street address");
        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("Postal Code");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(Margin.Bottom.SMALL);

        TextField city = new TextField("City");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        subSection.add(postalCode, city);

        ComboBox<String> stateSelect = new ComboBox<>("State");
        stateSelect.setRequiredIndicatorVisible(true);

        stateSelect.setItems(states);
        stateSelect.setVisible(false);
        countrySelect.setItems(countries);
        countrySelect
                .addValueChangeListener(e -> stateSelect.setVisible(countrySelect.getValue().equals("United States")));

        Checkbox sameAddress = new Checkbox("Billing address is the same as shipping address");
        sameAddress.addClassNames(Margin.Top.SMALL);

        Checkbox rememberAddress = new Checkbox("Remember address for next time");
        */
        previousSchoolDetails.add(stepTwo, header, previousGraduation);
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
