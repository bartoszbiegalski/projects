package org.gogroup.components.gameComponents.PlayerInfoVBox;

import javafx.scene.control.Label;

public class PlayerLabel extends Label {

    public PlayerLabel(String name)
    {
        setText(name);
        setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-text-fill: #333333; " +
                        "-fx-background-color: #f0f0f0; " +
                        "-fx-padding: 10px; " +
                        "-fx-border-color: #cccccc; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"
        );
    }

    public Label getLabel()
    {
        return this;
    }

}
