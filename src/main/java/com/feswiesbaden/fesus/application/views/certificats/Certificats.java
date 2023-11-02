package com.feswiesbaden.fesus.application.views.certificats;

import com.feswiesbaden.fesus.application.components.Buttons;
import com.feswiesbaden.fesus.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
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
import org.springframework.security.web.csrf.CsrfLogoutHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Einschulungsformular")
@Route(value = "certificats", layout = MainLayout.class)
@PermitAll
@PreserveOnRefresh
public class Certificats extends Div{

    //private ArrayList<Checkbox> mandatoryCheckboxes = new ArrayList<>();
    private Button send;
    private static String dsgvoStaticParagraph;
    private static String schoolregulationParagraphOne;
    private static String schoolregulationParagraphTwo;
    private static String schoolregulationParagraphThree;
    private static String schoolregulationParagraphFour;
    private static String schoolregulationParagraphFive;
    static{
        dsgvoStaticParagraph = "Mit der Anmeldung an der Friedrich-Ebert-Schule wird für jede Schülerin und jeden Schüler bzw. alle Studierenden eine Akte angelegt." +
                "In dieser Akte werden zunächst die auf dem Stammblatt ausgedruckten Daten erfasst." +
                "\nIm Verlauf des Schulbesuchs werden weitere Daten ergänzt wie z. B. Noten, erreichte Abschlüsse, Zeugnistermine.\n" +
                "\n" +
                "Die Datenhaltung geschieht sowohl in Papierform als auch in elektronischer Form in der sog. Lehrer- und Schülerdatenbank (LUSD).\n" +
                "\n" +
                "Die Grundlage für die Datenerhebung und die weitere Datenverarbeitung ist im Hessischen Schulgesetz §83, " +
                "in der Verordnung über die Verarbeitung personen-bezogener Daten in Schulen und statische Erhebungen an Schulen und im Hessischen Gesetz zur " +
                "Anpassung des Hessischen Datenschutzrechtes an die sog. Datenschutz-grundverordnung der Europäischen Union (DS-GVO) geregelt.\n" +
                "\n" +
                "Sie haben das Anrecht, nach Anmeldung die Daten bzw. die Schülerakte einzusehen. In solchen Fällen beantragen Sie dies bitte schriftlich bei der Schulleitung.\n" +
                "\n" +
                "Mit dieser Einverständniserklärung stimmen Sie außerdem zu, dass Fotos von Schulveranstaltungen (Projekte, Aktions- und Informationstage)" +
                "auf der Website oder in Flyern der Friedrich-Ebert-Schule veröffentlicht oder zum Zwecke der Veröffentlichung an die Presse (z.B. für einen Zeitungsartikel) weitergegeben werden dürfen. Dies gilt auch für die Namen erfolgreicher Absolventen der Schule." +
                "\n\nFalls Sie einer Veröffentlichung in den in diesem Absatz genannten Medien nicht zustimmen möchten, bitten wir Sie um einen schriftlichen Widerspruch.";

        schoolregulationParagraphOne = "Die FES in Wiesbaden ist ein Zentrum für Aus- und Weiterbildung im Technik- und " +
                "Dienstleistungsbereich. Es werden zurzeit rund 2200 Schülerinnen und Schüler sowie Studierende " +
                "unterrichtet. Um die hohen Qualitätsstandards in den unterschiedlichen Aus- und Weiterbildungsgängen " +
                "auf Dauer zu sichern, bedarf es neben der Bereitschaft zur ständigen Innovation auch der Bereitschaft " +
                "zur Fairness und Toleranz im Umgang miteinander. Toleranz und Fairness lassen sich leichter leben," +
                "wenn auch die Umgebung stimmt. Deshalb gilt es auch, auf den pfleglichen Umgang mit dem Gebäude " +
                "selbst sowie seinen Einrichtungen zu achten. Aus diesen Überlegungen ist die nachstehende " +
                "Schulordnung abgeleitet.\n\n";

        schoolregulationParagraphTwo = "1.1 Im Schulgebäude und auf dem Schulgrundstück verhalten sich alle so, dass weder Mitschüler noch " +
                "andere Personen belästigt, gefährdet oder verletzt werden. Ferner ist darauf zu achten, dass das " +
                "Schulgebäude sowie seine Einrichtungen nicht beschmutzt und beschädigt werden.\n" +
                "1.2 Rauchen ist im Schulgebäude und auf dem Schulgelände nicht gestattet (Hess. Schulgesetz §3(9))." +
                "Im Schulgebäude und auf dem Schulgrundstück, insbesondere im Bereich der Eingangstüren, ist das " +
                "Spucken untersagt.\n" +
                "1.3 Feueralarm wird durch Sirenen angezeigt. Bei Feueralarm verlassen alle Schüler unverzüglich auf " +
                "den ausgeschilderten Fluchtwegen das Gebäude und gehen zu dem Sammelplatz, der im " +
                "Brandalarmplan ausgewiesen ist. (Der Plan befindet sich im Schaukasten seitlich des " +
                "Haupteingangs)\n" +
                "1.4 Bei absichtlichen Sachbeschädigungen sowie Diebstählen muss der Verursacher Schadenersatz " +
                "leisten und mit strafrechtlichen Folgen rechnen.\n" +
                "1.5 Das Einstellen und die Verwendung von Fahrrädern, Rollern und ähnlichen Fortbewegungsmitteln im " +
                "Schulgebäude ist verboten.\n\n";

        schoolregulationParagraphThree = "2.1 Jede Klassengemeinschaft sorgt dafür, dass der Klassenraum nach dem Unterricht in sauberem " +
                "Zustand verlassen wird. Die Stühle sind hoch zu stellen. Fenster und Fluchttüren sind zu schließen.\n" +
                "2.2 Für Werkstätten, Labors sowie PC-Räume gelten besondere Nutzungsordnungen, die im Raum " +
                "aushängen.\n" +
                "2.3 Über die Verwendung von mobilen Endgeräten im Unterricht entscheidet die Lehrkraft.\n" +
                "2.4 Essen und Trinken in den Unterrichtsräumen ist nicht erwünscht und in Laborräumen, in Werkstätten " +
                "und PC-Räumen grundsätzlich nicht gestattet.\n" +
                "2.5 Der Aufenthalt auf den Fluchtwegen, Feuertreppen und Balkonen ist untersagt.\n" +
                "2.6 Jeder Schüler/jede Schülerin haftet selbst für sein/ihr Eigentum.\n" +
                "2.7 Jeder Schüler/jede Schülerin ist verpflichtet, festgestellte Mängel oder Schäden an " +
                "Einrichtungsgegenständen unverzüglich zu melden (Lehrer/in, Hausmeister/in, Sekretariat)\n\n";

        schoolregulationParagraphFour = "3.1 Bei Unterrichtsversäumnissen werden dem Klassenlehrer bzw. der Klassenlehrerin die Gründe für " +
                "das Fehlen durch den Schüler/die Schülerin bzw. bei nicht volljährigen Schülern/Schülerinnen durch " +
                "den Erziehungsberechtigten innerhalb folgender Fristen schriftlich mitgeteilt:\n\n" +
                "Berufsschule: am nächsten Berufsschultag \n" +
                "Blockklassen: bis zum Beginn der nächsten Blockwoche\n" +
                "Vollzeitklassen: spätestens nach drei Fehltagen\n\n" +
                "Bei Unterrichtsversäumnissen werden dem Klassenlehrer bzw. der Klassenlehrerin die Gründe für " +
                "das Fehlen schriftlich mitgeteilt. Bei nicht volljährigen Schülern/Schülerinnen muss ein " +
                "Erziehungsberechtigter unterschreiben, bei Berufsschülern/innen muss der Betrieb gegenzeichnen. " +
                "Wird das Fehlen mit einer Krankheit begründet, ist in der Regel eine ärztliche Bescheinigung " +
                "notwendig. \n" +
                "3.3 Betrieblicher Urlaub kann nur während der unterrichtsfreien Zeit genommen werden. Während der " +
                "Schulzeit können Schüler/Schülerinnen nur aus zwingenden persönlichen oder betrieblichen Gründen " +
                "und nur auf schriftlichen Antrag beurlaubt werden. Der Antrag muss 14 Tage vor Urlaubsbeginn dem " +
                "Klassenlehrer vorliegen.\n" +
                "3.4 Änderungen der persönlichen Daten (z.B. Umzug) sind unverzüglich dem Klassenlehrer bzw. der " +
                "Klassenlehrerin mitzuteilen\n\n";

        schoolregulationParagraphFive = "4.1 Das Schulgebäude wird um 7.00 Uhr geöffnet.\n" +
                "4.2 Der Unterricht beginnt um 7.30 Uhr.\n" +
                "4.3 Die unterrichtenden Lehrer bzw. Lehrerinnen schließen die Klassenräume auf und zu.\n" +
                "4.4 Falls 15 Minuten nach Unterrichtsbeginn kein Lehrer bzw. Lehrerin erschienen ist, wird dies im Sekretariat mitgeteilt.\n" +
                "4.5 Außerhalb der Unterrichtszeiten halten sich alle Schüler/Schülerinnen in der Regel nur im Pausenhof " +
                "und in der Pausenhalle auf.\n" +
                "4.6 Nur Schüler/Schülerinnen der Klassen 11 (Berufsschule Mittelstufe, BFS 11, FOS, FT und FV) bis 13 " +
                "dürfen in den Pausen oder Zwischenstunden das Schulgrundstück verlassen. Schüler/Schülerinnen " +
                "der Klassen 10 müssen sich das Verlassen des Schulgrundstückes von dem Klassenlehrer bzw. der " +
                "Klassenlehrerin oder des/der Aufsicht führenden Lehrer /in genehmigen lassen.\n" +
                "4.7 Der Hausmeister und die Lehrer bzw. Lehrerinnen haben das Recht Schüler zur Ordnung zu ermahnen.\n" +
                "4.8 Das Hausrecht wird durch den Schulleiter/die Schulleiterin ausgeübt.\n" +
                "4.9 Bei Verstößen gegen die Hausordnung muss der Schüler oder die Schülerin mit Ordnungsmaßnahmen gemäß § 82 Hessisches Schulgesetz rechnen.\n";
    }

    public Certificats() {
        addClassNames("certificats-view");
        addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.Height.FULL);

        Main content = new Main();
        content.addClassNames(LumoUtility.Display.FLEX, LumoUtility.AlignItems.START, LumoUtility.JustifyContent.CENTER, LumoUtility.MaxWidth.SCREEN_MEDIUM,
                LumoUtility.Margin.Left.XLARGE, LumoUtility.Margin.Right.AUTO, LumoUtility.Padding.Bottom.LARGE, LumoUtility.Padding.Horizontal.LARGE);

        content.add(createCertificatsView());
        add(content);
    }


    private Component createCertificatsView() {
        Section certificatesForm = new Section();
        certificatesForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Einschulen");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Alle Pflichtfelder ausfüllen.");
        note.addClassNames(Margin.Bottom.SMALL, Margin.Top.NONE, TextColor.SECONDARY);

        certificatesForm.add(header, note);

        certificatesForm.add(new Hr());
        certificatesForm.add(certificatsView());
        certificatesForm.add(new Hr());
        certificatesForm.add(createFooter());

        return certificatesForm;
    }

    private Component certificatsView(){
        Section certificatsSection = new Section();
        certificatsSection.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepSix = new Paragraph("Schritt 1/6");
        stepSix.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Zertifikate");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        Button readDsgvoButton = new Button("Datenschutzgrundverordnung lesen");
        readDsgvoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button readSchoolRegulationButton = new Button("Schulordnung lesen");
        readSchoolRegulationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        readSchoolRegulationButton.addClassName(Margin.Bottom.MEDIUM);
        /*
        * DSGVO Layout und Dialog einbauen
        * */
        HorizontalLayout dsgvoLayout = new HorizontalLayout();

        Checkbox dsgvoCheckbox = new Checkbox();
        dsgvoCheckbox.setRequiredIndicatorVisible(true);

        Paragraph dsgvoParagraph = new Paragraph("Ich stimme der Datenschutzgrundverordnung der Friedrich-Ebert-Schule zu.");
        dsgvoParagraph.addClassName(Margin.NONE);


        //Dialog DSGVO
        Dialog dsgvoDialog = new Dialog("Datenschutzgrundverordnung der Friedrich-Ebert-Schule in Wiesbaden");
        dsgvoDialog.addClassNames(Margin.Bottom.XLARGE, Margin.Top.XLARGE);

        //readDsgvoParagraph.addClickListener(e-> dsgvoDialog.open());
        readDsgvoButton.addClickListener(e-> dsgvoDialog.open());

        VerticalLayout dialogLayout = new VerticalLayout();
        dsgvoDialog.add(dialogLayout);
        dsgvoDialog.setWidth("60%");

        Paragraph dsgvoDialogParagraph = new Paragraph();
        dsgvoDialogParagraph.setText(dsgvoStaticParagraph);
        dsgvoDialogParagraph.getStyle().set("white-space", "pre-line");

        Button acceptButtonDialog = Buttons.createPrimaryButton("Ich stimme zu");
        acceptButtonDialog.addClickListener(e->{
            dsgvoDialog.close();
            dsgvoCheckbox.setValue(true);
        });
        Button declineButtonDialog = Buttons.createSecondaryButton("Ich stimme nicht zu");
        declineButtonDialog.addClickListener( e->{
            dsgvoDialog.close();
            dsgvoCheckbox.setValue(false);
        });

        dsgvoDialog.getFooter().add(declineButtonDialog, acceptButtonDialog);
        dialogLayout.add(dsgvoDialogParagraph);

        dsgvoLayout.add(dsgvoCheckbox, dsgvoParagraph);

        /*
        * Schulordnung Dialog und Layout einbauen
        * */
        HorizontalLayout schoolRegulationsLayout = new HorizontalLayout();
        Checkbox schoolRegulationsCheckbox = new Checkbox();
        schoolRegulationsCheckbox.setRequiredIndicatorVisible(true);

        Paragraph schoolRegulationsParagraph = new Paragraph("Ich stimme der Schulverordnung zu.");
        schoolRegulationsParagraph.addClassName(Margin.NONE);

        //Dialog Schulordnung
        Dialog schoolRegulationDialog = new Dialog("Schulverordnung der Friedrich-Ebert-Schule in Wiesbaden");
        schoolRegulationDialog.addClassNames(Margin.Top.XLARGE, Margin.Bottom.XLARGE);

        readSchoolRegulationButton.addClickListener(e-> schoolRegulationDialog.open());

        VerticalLayout schoolDialogLayout = new VerticalLayout();
        schoolRegulationDialog.add(schoolDialogLayout);
        schoolRegulationDialog.setWidth("60%");

        //Inhalt Dialog
        Paragraph schoolDialogParagraph = new Paragraph();

        schoolDialogParagraph.add(new H4("Allgemeines\n"));
        schoolDialogParagraph.add(schoolregulationParagraphOne);
        schoolDialogParagraph.add(new H4("Verhalten im Schulgebäude und auf dem Schulgrundstück\n"));
        schoolDialogParagraph.add(schoolregulationParagraphTwo);
        schoolDialogParagraph.add(new H4("Verhalten in den Klassenräumen\n"));
        schoolDialogParagraph.add(schoolregulationParagraphThree);
        schoolDialogParagraph.add(new H4("Teilnahme am Unterricht\n"));
        schoolDialogParagraph.add(schoolregulationParagraphFour);
        schoolDialogParagraph.add(new H4("Unterrichts- und Pausenzeiten\n"));
        schoolDialogParagraph.add(schoolregulationParagraphFive);

        schoolDialogParagraph.getStyle().set("white-space", "pre-line");

        Button schoolAcceptButtonDialog = Buttons.createPrimaryButton("Ich stimme zu");
        schoolAcceptButtonDialog.addClickListener( e->{
            schoolRegulationDialog.close();
            schoolRegulationsCheckbox.setValue(true);
        });

        Button schoolDeclineButtonDialog = Buttons.createSecondaryButton("Ich stimme nicht zu");
        schoolDeclineButtonDialog.addClickListener( e->{
            schoolRegulationDialog.close();
            schoolRegulationsCheckbox.setValue(false);
        });

        Anchor schoolregulationsDialogAnchor = new Anchor("https://fes-wiesbaden.de/wp-content/uploads/2019/07/FES-Schulordnung.pdf", "Schulordnung online lesen");
        schoolregulationsDialogAnchor.setTarget("_blank");


        schoolRegulationDialog.getFooter().add(schoolregulationsDialogAnchor, schoolDeclineButtonDialog, schoolAcceptButtonDialog);
        schoolDialogLayout.add(schoolDialogParagraph);

        schoolRegulationsLayout.add(schoolRegulationsCheckbox, schoolRegulationsParagraph);


        //Unterstützungsangebote
        HorizontalLayout supportOffersLayout = new HorizontalLayout();
        Checkbox supportoffersCheckbox = new Checkbox();
        Anchor supportoffersAnchor = new Anchor("https://fes-wiesbaden.de/wp-content/uploads/2019/07/Unterstuetzungsangebote_SuS.pdf", "Ich bin über die Unterstützungsangebote informiert worden.");
        supportoffersAnchor.setTarget("_blank");
        supportOffersLayout.add(supportoffersCheckbox, supportoffersAnchor);

        //Infektionsschutzgesetz
        HorizontalLayout infectionActLayout = new HorizontalLayout();
        Checkbox infectionActCheckbox = new Checkbox();

        Anchor infectionActAnchor = new Anchor("https://rp-giessen.hessen.de/versorgung-und-familie/soziales-entschaedigungsrecht/infektionsschutzgesetz-ifsg", "Ich bin über das Infektionsschutzgesetz informiert.");
        infectionActAnchor.setTarget("_blank");
        infectionActLayout.add(infectionActCheckbox, infectionActAnchor);

        //Masernschutzgesetz
        HorizontalLayout measlesActLayout = new HorizontalLayout();
        Checkbox measlesActCheckbox = new Checkbox();
        Anchor measlesActAnchor = new Anchor("https://www.masernschutz.de/", "Ich bin über das Masernschutzgesetz informiert.");
        measlesActAnchor.setTarget("_blank");
        measlesActLayout.add(measlesActCheckbox, measlesActAnchor);

        /*infectionActCheckbox.addValueChangeListener(e->{
            if (e.getValue() && dsgvoCheckbox.getValue() && measlesActCheckbox.getValue() && supportoffersCheckbox.getValue() && schoolRegulationsCheckbox.getValue()){
                send.setEnabled(true);
            }
            else{
                send.setEnabled(false);
            }
        });
        dsgvoCheckbox.addValueChangeListener(e->{
            if (e.getValue() && infectionActCheckbox.getValue() && measlesActCheckbox.getValue() && supportoffersCheckbox.getValue() && schoolRegulationsCheckbox.getValue()){
                send.setEnabled(true);
            }
            else{
                send.setEnabled(false);
            }
        });
        measlesActCheckbox.addValueChangeListener(e->{
            if (e.getValue() && infectionActCheckbox.getValue() && dsgvoCheckbox.getValue() && supportoffersCheckbox.getValue() && schoolRegulationsCheckbox.getValue()){
                send.setEnabled(true);
            }
            else{
                send.setEnabled(false);
            }
        });
        supportoffersCheckbox.addValueChangeListener(e->{
            if (e.getValue() && infectionActCheckbox.getValue() && dsgvoCheckbox.getValue() && measlesActCheckbox.getValue() && schoolRegulationsCheckbox.getValue()){
                send.setEnabled(true);
            }
            else{
                send.setEnabled(false);
            }
        });
        schoolRegulationsCheckbox.addValueChangeListener(e->{
            if (e.getValue() && infectionActCheckbox.getValue() && dsgvoCheckbox.getValue() && measlesActCheckbox.getValue() && supportoffersCheckbox.getValue()){
                send.setEnabled(true);
            }
            else{
                send.setEnabled(false);
            }
        });*/

        certificatsSection.add(stepSix, header, readDsgvoButton, readSchoolRegulationButton, dsgvoLayout, schoolRegulationsLayout, supportOffersLayout, infectionActLayout, measlesActLayout);


        return certificatsSection;

    }


    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassNames(Gap.MEDIUM);
        buttonLayout.setWidth("100%");

        Button cancel = Buttons.createSecondaryButton("Abbruch", "about");

        send = Buttons.createPrimaryButton("Weiter", "right", "enroll-student");
        //send.setEnabled(false);


        buttonLayout.add(cancel, send);
        footer.add(buttonLayout);
        return footer;
    }

}
