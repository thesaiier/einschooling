package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoUtility;
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
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class EnrollStudentView extends Div {

    private Section mainForm;

    private HashMap<Integer, Component> showSectionsMap = new HashMap<Integer, Component>();
    private int currentSectionCounter = 1;
    private Hr breakline = new Hr();

    private static final Set<String> apprenticeships = new LinkedHashSet<>();
    private static final Set<String> metaltechnology = new LinkedHashSet<>();
    private static final Set<String> electricalengineering = new LinkedHashSet<>();
    private static final Set<String> ittechnology = new LinkedHashSet<>();
    private static final Set<String> eventtechnology = new LinkedHashSet<>();
    private static final Set<String> businessprofessions = new LinkedHashSet<>();
    private static final Set<String> countries = new LinkedHashSet<>();
    static{
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

        apprenticeships.addAll(Arrays.asList("Metalltechnik", "Elektrotechnik", "IT-Technik", "Veranstaltungstechnik", "Kaufmännische Berufe"));

        metaltechnology.addAll(Arrays.asList("Industriemechaniker", "Teilezurichter", "Werkzeugmechaniker", "Zerspanungsmechaniker", "Konstruktionsmechaniker",
                "Anlagenmechaniker für Sanitär-, Heizungs- und Klimatechnik", "KFZ-Mechatroniker", "Mechatroniker"));
        electricalengineering.addAll(Arrays.asList("Energie- und Gebäudetechnik", "Betriebstechnik", "Geräte und Systeme"));
        ittechnology.addAll(Arrays.asList("Fachinformatiker - Anwendungsentwicklung", "Fachinformatiker - Systemintegration",
                "Fachinformatiker - Digitale Vernetzung", "Fachinformatiker - Daten und Prozessanalyse", "Fachinformatiker - Zweijährige und DXC",
                "IT-Systemelektroniker"));
        eventtechnology.addAll(Arrays.asList("Fachkraft für Veranstaltungstechnik"));
        businessprofessions.addAll(Arrays.asList("Fachkraft für Veranstaltungstechnik"));
    }

    public EnrollStudentView() {
        addClassNames("enroll-student-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.FLEX, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Left.XLARGE, Margin.Right.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createSchoolingForm());

        add(content);
    }

    private Component createSchoolingForm() {
        mainForm = new Section();
        mainForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, TextColor.SECONDARY);

        mainForm.add(header, note);

        mainForm.add(breakline);
        mainForm.add(createPersonalDetailsSection());
        mainForm.add(createLifeDetailsSection());
        mainForm.add(createSchoolDetailsSection());
        mainForm.add(createApprenticeshipDetails());
        mainForm.add(createContactpersonDetails());
        mainForm.add(breakline);
        mainForm.add(createFooter());

        return mainForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        showSectionsMap.put(1, personalDetails);

        personalDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);


        Paragraph stepOne = new Paragraph("Schritt 2/6");
        stepOne.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Personaldaten");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);


        ComboBox<String> apprenticeshipComboBox = new ComboBox<>("Ausbildungsberuf");
        apprenticeshipComboBox.setRequiredIndicatorVisible(true);
        apprenticeshipComboBox.addClassNames(Margin.Bottom.SMALL);
        apprenticeshipComboBox.setItems(apprenticeships);

        ComboBox<String> apprenticeshipFocusComboBox = new ComboBox<>("Fachrichtung/Schwerpunkt");
        apprenticeshipFocusComboBox.setReadOnly(true);
        apprenticeshipFocusComboBox.setRequiredIndicatorVisible(true);
        apprenticeshipComboBox.addClassNames(Margin.Bottom.SMALL);

        apprenticeshipComboBox.addValueChangeListener(e->{
            String value = apprenticeshipComboBox.getValue();

            switch (value){
                case "Metalltechnik":
                    apprenticeshipFocusComboBox.setItems(metaltechnology);
                    apprenticeshipFocusComboBox.setReadOnly(false);
                    break;
                case "Elektrotechnik":
                    apprenticeshipFocusComboBox.setItems(electricalengineering);
                    apprenticeshipFocusComboBox.setReadOnly(false);
                    break;
                case "IT-Technik":
                    apprenticeshipFocusComboBox.setItems(ittechnology);
                    apprenticeshipFocusComboBox.setReadOnly(false);
                    break;
                case "Veranstaltungstechnik":
                    apprenticeshipFocusComboBox.setItems(eventtechnology);
                    apprenticeshipFocusComboBox.setReadOnly(false);
                    break;
                case "Kaufmännische Berufe":
                    apprenticeshipFocusComboBox.setItems(businessprofessions);
                    apprenticeshipFocusComboBox.setReadOnly(false);
                    break;
            }
        });

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

        RadioButtonGroup<String> genderRadio = new RadioButtonGroup<>();
        genderRadio.setLabel("Geschlecht");
        genderRadio.setItems("Männlich", "Weiblich", "Divers");
        genderRadio.setValue("Männlich");
        genderRadio.addClassNames(Margin.Bottom.SMALL);

        HorizontalLayout addressLayout = new HorizontalLayout();
        addressLayout.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField address = new TextField("Straßenname");
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);
        address.setWidth("70%");

        TextField houseNumber = new TextField("Hausnummer");
        houseNumber.setRequiredIndicatorVisible(true);
        houseNumber.addClassNames(Margin.Bottom.SMALL);
        houseNumber.setWidth("25%");

        addressLayout.add(address, houseNumber);

        HorizontalLayout cityLayout = new HorizontalLayout();
        cityLayout.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("PLZ");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("[\\d \\p{L}]*");
        postalCode.addClassNames(Margin.Bottom.SMALL);
        postalCode.setWidth("25%");

        TextField city = new TextField("Stadt");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        cityLayout.add(postalCode, city);

        personalDetails.add(stepOne, header, apprenticeshipComboBox, apprenticeshipFocusComboBox, nameLayout, genderRadio, addressLayout, cityLayout);
        return personalDetails;
    }

    private Section createLifeDetailsSection() {
        Section previousLifeDetails = new Section();
        previousLifeDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        previousLifeDetails.setVisible(false);

        showSectionsMap.put(2, previousLifeDetails);

        Paragraph stepTwo = new Paragraph("Schritt 3/6");
        stepTwo.addClassNames(Margin.NONE, LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);

        H3 header = new H3("Personaldaten");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, LumoUtility.FontSize.XXLARGE);

        Div birthplacesDiv = new Div();
        birthplacesDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField birthPlace = new TextField("Geburtsort");
        birthPlace.setRequiredIndicatorVisible(true);
        birthPlace.addClassNames(Margin.Bottom.SMALL);

        ComboBox<String> birthCountry = new ComboBox<>("Geburtsland");
        birthCountry.setRequiredIndicatorVisible(true);
        birthCountry.addClassNames(Margin.Bottom.SMALL);
        birthCountry.setItems(countries);

        birthplacesDiv.add(birthPlace, birthCountry);

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

        DatePicker dateSinceGermany = new DatePicker("Datum des Zuzugs nach Deutschland (optional)");
        dateSinceGermany.addClassNames(Margin.Bottom.SMALL);

        Div languageDiv = new Div();
        languageDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);
        TextField firstLanguage = new TextField("Familiensprache 1");
        firstLanguage.setRequiredIndicatorVisible(true);
        firstLanguage.addClassNames(Margin.Bottom.SMALL);

        TextField secondLanguage = new TextField("Familiesprache 2 (optional)");
        secondLanguage.addClassNames(Margin.Bottom.SMALL);

        languageDiv.add(firstLanguage, secondLanguage);

        Div nationalityDiv = new Div();
        nationalityDiv.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField firstNationality = new TextField("Staatsangehörigkeit 1");
        firstNationality.setRequiredIndicatorVisible(true);
        firstNationality.addClassNames(Margin.Bottom.SMALL);

        TextField secondNationality = new TextField("Staatsangehörigkeit 2 (optional)");
        secondNationality.addClassNames(Margin.Bottom.SMALL);

        nationalityDiv.add(firstNationality, secondNationality);

        previousLifeDetails.add(stepTwo, header, birthplacesDiv, birthDate, email, phone, dateSinceGermany, languageDiv, nationalityDiv);
        return previousLifeDetails;
    }
    private Section createSchoolDetailsSection() {
        Section previousSchoolDetails = new Section();
        previousSchoolDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        previousSchoolDetails.setVisible(false);
        showSectionsMap.put(3, previousSchoolDetails);

        Paragraph stepThree = new Paragraph("Schritt 4/6");
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
    private Section createApprenticeshipDetails() {
        Section apprenticeshipDetails = new Section();
        apprenticeshipDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        apprenticeshipDetails.setVisible(false);

        showSectionsMap.put(4, apprenticeshipDetails);

        Paragraph stepFive = new Paragraph("Schritt 5/6");
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
    private Section createContactpersonDetails() {
        Section contactpersonDetails = new Section();
        contactpersonDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        contactpersonDetails.setVisible(false);
        showSectionsMap.put(5, contactpersonDetails);

        Paragraph stepFive = new Paragraph("Schritt 6/6");
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

        contactpersonDetails.add(stepFive, header, familyNameLayout, adressLayout, contactLayout);
        return contactpersonDetails;
    }
    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassNames(LumoUtility.Gap.MEDIUM);
        buttonLayout.setWidth("100%");

        HorizontalLayout twoButtons = new HorizontalLayout();
        twoButtons.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM, Margin.Left.AUTO);

        Button cancel = Buttons.createSecondaryButton("Abbruch", "about");

        Button back = Buttons.createPrimaryButton("Zurück");
        back.setIcon(new Icon(VaadinIcon.ARROW_LEFT));

        Button send = Buttons.createSendButton("Weiter");

        back.addClickListener(e->{
            if (currentSectionCounter == 1){
                back.getUI().get().navigate("certificats");
            }else{
                currentSectionCounter--;
                for ( Component temp : showSectionsMap.values()){
                    temp.setVisible(false);
                }
                showSectionsMap.get(currentSectionCounter).setVisible(true);
            }
            if (currentSectionCounter != showSectionsMap.size()) send.setText("Weiter");


        });
        send.addClickListener(e->{
            currentSectionCounter++;
            if (currentSectionCounter <= showSectionsMap.size()){
                for ( Component temp : showSectionsMap.values()){
                    temp.setVisible(false);
                }
                showSectionsMap.get(currentSectionCounter).setVisible(true);
            }
            else if(currentSectionCounter > showSectionsMap.size()){
                send.getUI().get().navigate("about");
            }
            if (currentSectionCounter == showSectionsMap.size()) send.setText("Senden");

        });
        twoButtons.add(back, send);

        buttonLayout.add(cancel, twoButtons);
        footer.add(buttonLayout);
        return footer;
    }


}
