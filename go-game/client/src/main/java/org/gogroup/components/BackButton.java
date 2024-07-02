package org.gogroup.components;

import javafx.scene.control.Button;

public class BackButton extends Button {

    public BackButton() {
        setText("←");
        setWidth(230);
        setHeight(160);
        setStyle("-fx-background-color: #0bffa0;");
    }

    public BackButton returnBackButton()
    {
        return this;
    }

}

