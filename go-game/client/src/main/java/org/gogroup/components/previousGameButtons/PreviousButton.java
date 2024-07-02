package org.gogroup.components.previousGameButtons;

import javafx.scene.control.Button;

public class PreviousButton extends Button {

    public PreviousButton(String buttonText) {
        setText(buttonText);
        setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-border-color: #45A049; " +
                        "-fx-border-width: 2px; " +
                        "-fx-background-insets: 0,1,2,3;" +
                        "-fx-background-radius: 3,2,2,2;" +
                        "-fx-padding: 3 30 3 30;"
        );
    }
}
