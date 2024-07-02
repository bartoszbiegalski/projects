package org.gogroup.components.gameComponents.bottomVBox;

import javafx.scene.control.Button;

public class GameButton extends Button {

    public GameButton(String text)
    {
        setText(text);
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
