package com.feswiesbaden.fesus.application.views;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.Lumo;

public class DarkthemeCheckbox extends VerticalLayout {

    public DarkthemeCheckbox() {
        var themeToggle = new Checkbox("Dark theme");
        themeToggle.addValueChangeListener(e -> {
            setTheme(e.getValue());
        });

        add(themeToggle);
    }

    private void setTheme(boolean dark) {
        var js = "document.documentElement.setAttribute('theme', $0)";

        getElement().executeJs(js, dark ? Lumo.DARK : Lumo.LIGHT);
    }
}