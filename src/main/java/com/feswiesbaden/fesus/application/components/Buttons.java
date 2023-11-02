package com.feswiesbaden.fesus.application.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public abstract class Buttons {

    public static Button createPrimaryButton(String text, String direction, String destination){
        Button tempButton;
        if (direction.equals("right")){
            tempButton = new Button(text, new Icon(VaadinIcon.ARROW_RIGHT));
            tempButton.setIconAfterText(true);

        }
        else if (direction.equals("left")) {
            tempButton = new Button(text, new Icon(VaadinIcon.ARROW_LEFT));
        }
        else {
            tempButton = null;
        }

        tempButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        tempButton.addClickListener(event -> {
            tempButton.getUI().ifPresent(ui -> ui.navigate(destination));
        });

        return tempButton;
    }

    public static Button createSendButton(String text, String destination){
        Button tempButton = new Button(text, new Icon(VaadinIcon.ARROW_RIGHT));
        tempButton.setIconAfterText(true);

        tempButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        tempButton.addClickListener(event -> {
            tempButton.getUI().ifPresent(ui -> ui.navigate(destination));
        });

        return tempButton;
    }
    public static Button createSendButton(String text){
        Button tempButton = new Button(text, new Icon(VaadinIcon.ARROW_RIGHT));
        tempButton.setIconAfterText(true);

        tempButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        return tempButton;
    }

    public static Button createPrimaryButton(String text, String destination){
        Button tempButton = new Button(text);

        tempButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        tempButton.addClickListener(event -> {
            tempButton.getUI().ifPresent(ui -> ui.navigate(destination));
        });

        return tempButton;
    }
    public static Button createPrimaryButton(String text){
        Button tempButton = new Button(text);
        tempButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        return tempButton;
    }
    public static Button createSecondaryButton(String text, String destination){
        Button tempButton = new Button(text);

        tempButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        tempButton.addClickListener(event -> {
            tempButton.getUI().ifPresent(ui -> ui.navigate(destination));
        });

        return tempButton;
    }
    public static Button createSecondaryButton(String text){
        Button tempButton = new Button(text);
        tempButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return tempButton;
    }
}
