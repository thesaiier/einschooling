package com.feswiesbaden.fesus.application.views.enrollstudent;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.*;
import jakarta.annotation.security.PermitAll;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Einschulungsformular")
@Route(value = "enroll-student-two", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class EnrollStudentViewSectionTwo extends Div{

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
    }

    public EnrollStudentViewSectionTwo(){
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
        ProgressBar progressBar = new ProgressBar(1, 6, 2);
        progressBar.addClassNames(Margin.Top.XLARGE);
        Paragraph progressParagraph = new Paragraph("Einschulungsfortschritt");
        progressParagraph.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.TERTIARY);
        checkoutForm.add(progressBar, progressParagraph, header, note);

        checkoutForm.add(new Hr());
        checkoutForm.add(createLifeDetailsSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }
    private Section createLifeDetailsSection() {
        Section previousLifeDetails = new Section();
        previousLifeDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Schritt 2/5");
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
    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassNames(Gap.MEDIUM);
        buttonLayout.setWidth("100%");

        Button cancel = Buttons.createSecondaryButton("Abbruch", "about");

        HorizontalLayout twoButtons = new HorizontalLayout();
        twoButtons.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM, Margin.Left.AUTO);


        Button send = Buttons.createPrimaryButton("Weiter", "right", "enroll-student-three");
        Button back = Buttons.createPrimaryButton("Zurück", "left", "enroll-student");

        twoButtons.add(back, send);

        buttonLayout.add(cancel, twoButtons);
        footer.add(buttonLayout);
        return footer;
    }
}
