package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import jakarta.annotation.security.PermitAll;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student", layout = MainLayout.class)
@PermitAll
public class EnrollStudentView extends Div {

    private static final Set<String> states = new LinkedHashSet<>();
    private static final Set<String> countries = new LinkedHashSet<>();

    static {
        states.addAll(Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
                "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
                "West Virginia", "Wisconsin", "Wyoming"));

        countries.addAll(Arrays.asList("Afghanistan", "Albanien", "Algerien", "Amerikanisch-Samoa", "Andorra", "Angola",
                "Anguilla", "Antarktis", "Antigua und Barbuda", "Argentinien", "Armenien", "Aruba", "Australien",
                "Aserbaidschan", "Bahamas", "Bahrain", "Bangladesch", "Barbados", "Belarus", "Belgien", "Belize",
                "Benin", "Bermuda", "Bhutan", "Bolivien", "Bosnien und Herzegowina", "Botsuana", "Bouvetinsel",
                "Brazilien", "Britisches Territorium im Indischen Ozean", "Britische Jungferninseln", "Brunei Darussalam", "Bulgarien",
                "Burkina Faso", "Burundi", "Kambodscha", "Kamerun", "Kanada", "Kap Verde", "Kaimaninseln",
                "Zentralafrikanische Republik", "Tschad", "Chile", "China", "Weihnachtsinsel", "Kokosinsel (Keeling)",
                "Kolumbien", "Komoren", "Kongo", "Cookinseln", "Costa Rica", "Kroatien", "Kuba", "Zypern",
                "Tschechien", "Dänemark", "Dschibuti", "Dominica", "Dominikanische Republik", "Osttimor", "Ecuador",
                "Ägypten", "El Salvador", "Äquatorialguinea", "Eritrea", "Estland", "Äthiopien", "Falklandinseln (Malvinen)",
                "Färöer", "Föderierte Staaten von Mikronesien", "Fidschi", "Finnland", "Frankreich", "Französisch-Guyana",
                "Französisch-Polynesien", "Französische Süd- und Antarktisgebiete", "Gabun", "Gambia", "Georgien", "Deutschland", "Ghana",
                "Gibraltar", "Griechenland", "Grönland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea",
                "Guinea-Bissau", "Guyana", "Haiti", "Heard und McDonaldinseln", "Honduras", "Hongkong",
                "Ungarn", "Island", "Indien", "Indonesien", "Iran", "Iraq", "Irland", "Israel", "Italien", "Elfenbeinküste",
                "Jamaika", "Japan", "Jordanien", "Kasachstan", "Kenia", "Kiribati", "Kuwait", "Kirgisistan", "Laos",
                "Lettland", "Libanon", "Lesotho", "Liberia", "Libyen", "Liechtenstein", "Litauen", "Luxemburg", "Macau",
                "Mazedonien", "Madagaskar", "Malawi", "Malaysia", "Malediven", "Mali", "Malta", "Marshallinseln",
                "Martinique", "Mauretanien", "Mauritius", "Mayotte", "Mexiko", "Moldawien", "Monaco", "Mongolei",
                "Montserrat", "Marokko", "Mosambik", "Myanmar", "Namibia", "Nauru", "Nepal", "Niederlande",
                "Netherlands Antilles", "Neukaledonien", "Neuseeland", "Nicaragua", "Niger", "Nigeria", "Niue",
                "Norfolkinsel", "Nordkorea", "Nördliche Marianen", "Norwegen", "Oman", "Österreich", "Pakistan", "Palau",
                "Panama", "Papua-Neuguinea", "Paraguay", "Peru", "Philippinen", "Pitcairninseln", "Polen", "Portugal",
                "Puerto Rico", "Katar", "Réunion", "Rumänien", "Russland", "Ruanda", "St. Kitts und Nevis",
                "St. Lucia", "St. Vincent und die Grenadinen", "Samoa", "San Marino", "São Tomé und Príncipe",
                "Saudi-Arabien", "Senegal", "Seychellen", "Sierra Leone", "Singapur", "Slowakei", "Slowenien",
                "Salomonen", "Somalia", "Südafrika", "Südgeorgien und die Südlichen Sandwichinseln",
                "Südkorea", "Spanien", "Sri Lanka", "St. Helena", "St. Pierre und Miquelon", "Sudan", "Suriname",
                "Svalbard und Jan Mayen", "Swasiland", "Schweden", "Schweiz", "Syrische Arabische Republik",
                "Taiwan", "Tadschikistan", "Tansania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad und Tobago",
                "Tunesien", "Türkei", "Turkmenistan", "Turks- und Caicosinseln", "Tuvalu", "Uganda", "Ukraine",
                "Vereinigte Arabische Emirate", "Vereinigtes Königreich", "Vereinigte Staaten", "Amerikanische Überseeinseln",
                "Amerikanische Jungferninseln", "Uruguay", "Usbekistan", "Vanuatu", "Vatikanstadt", "Venezuela",
                "Vietnam", "Wallis und Futuna", "Westsahara", "Jemen", "Jugoslawien", "Zaire", "Sambia",
                "Simbabwe"));
    }

    public EnrollStudentView() {
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
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(new Hr());
        checkoutForm.add(createPersonalDetailsSection());
        //checkoutForm.add(createSchoolDetailsSection());
        //checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepOne = new Paragraph("Schritt 1/3");
        stepOne.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Personaldaten");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        HorizontalLayout nameLayout = new HorizontalLayout();

        TextField firstName = new TextField("Vorname");
        firstName.setRequiredIndicatorVisible(true);
        firstName.setPattern("[\\p{L} \\-]+");
        firstName.addClassNames(Margin.Bottom.SMALL);

        TextField lastName = new TextField("Nachname");
        lastName.setRequiredIndicatorVisible(true);
        lastName.setPattern("[\\p{L} \\-]+");
        lastName.addClassNames(Margin.Bottom.SMALL);

        nameLayout.add(firstName, lastName);

        RadioButtonGroup<String> radioGender = new RadioButtonGroup<>();
        radioGender.setLabel("Geschlecht");
        radioGender.setItems("Männlich", "Weiblich", "Divers");
        radioGender.setValue("Männlich");
        radioGender.addClassNames(Margin.Bottom.SMALL);

        Div addressDiv = new Div();
        addressDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField address = new TextField("Straßenname");
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);
        address.setWidth("70%");

        TextField houseNumber = new TextField("Hausnummer");
        houseNumber.setRequiredIndicatorVisible(true);
        houseNumber.addClassNames(Margin.Bottom.SMALL);
        houseNumber.setWidth("25%");

        addressDiv.add(address, houseNumber);


        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("PLZ");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(Margin.Bottom.SMALL);
        postalCode.setWidth("25%");

        TextField city = new TextField("Stadt");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        subSection.add(postalCode, city);

        EmailField email = new EmailField("Email Adresse");
        email.setRequiredIndicatorVisible(true);
        email.addClassNames(Margin.Bottom.SMALL);

        TextField phone = new TextField("Telefonnummer");
        phone.setRequiredIndicatorVisible(true);
        phone.setPattern("[\\d \\-\\+]+");
        phone.addClassNames(Margin.Bottom.SMALL);

        DatePicker birthDate = new DatePicker("Geburtsdatum");
        birthDate.setRequiredIndicatorVisible(true);
        birthDate.addClassNames(Margin.Bottom.SMALL);

        HorizontalLayout birthPlaces = new HorizontalLayout();

        TextField birthPlace = new TextField("Geburtsort");
        birthPlace.setRequiredIndicatorVisible(true);
        birthPlace.addClassNames(Margin.Bottom.SMALL);

        ComboBox<String> birthCountry = new ComboBox<>("Geburtsland");
        birthCountry.setRequiredIndicatorVisible(true);
        birthCountry.addClassNames(Margin.Bottom.SMALL);
        birthCountry.setItems(countries);

        birthPlaces.add(birthPlace, birthCountry);

        personalDetails.add(stepOne, header, nameLayout, radioGender, addressDiv, subSection, email, phone, birthDate, birthPlaces);
        return personalDetails;
    }



    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Checkout 3/3");
        stepThree.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Personal details");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextField cardHolder = new TextField("Cardholder name");
        cardHolder.setRequiredIndicatorVisible(true);
        cardHolder.setPattern("[\\p{L} \\-]+");
        cardHolder.addClassNames(Margin.Bottom.SMALL);

        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField cardNumber = new TextField("Card Number");
        cardNumber.setRequiredIndicatorVisible(true);
        cardNumber.setPattern("[\\d ]{12,23}");
        cardNumber.addClassNames(Margin.Bottom.SMALL);

        TextField securityCode = new TextField("Security Code");
        securityCode.setRequiredIndicatorVisible(true);
        securityCode.setPattern("[0-9]{3,4}");
        securityCode.addClassNames(Flex.GROW, Margin.Bottom.SMALL);
        securityCode.setHelperText("What is this?");

        subSectionOne.add(cardNumber, securityCode);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        Select<String> expirationMonth = new Select<>();
        expirationMonth.setLabel("Expiration month");
        expirationMonth.setRequiredIndicatorVisible(true);
        expirationMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        Select<String> expirationYear = new Select<>();
        expirationYear.setLabel("Expiration year");
        expirationYear.setRequiredIndicatorVisible(true);
        expirationYear.setItems("22", "23", "24", "25", "26");

        subSectionTwo.add(expirationMonth, expirationYear);

        paymentInfo.add(stepThree, header, cardHolder, subSectionTwo);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Schließen");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button send = new Button("Senden", new Icon(VaadinIcon.ARROW_RIGHT));
        send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        send.setIconAfterText(true);

        Anchor weiter = new Anchor("enroll-student-two", "part2");

        footer.add(cancel, send, weiter);
        return footer;
    }


}
