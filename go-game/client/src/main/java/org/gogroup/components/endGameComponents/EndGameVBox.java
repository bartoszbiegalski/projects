package org.gogroup.components.endGameComponents;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.gogroup.components.BackButton;


public class EndGameVBox extends VBox {

    private BackButton backButton;

    public EndGameVBox(String string) {
        this.backButton = new BackButton();
        Label label = new Label(string);
        setStyle(
                "-fx-font-size: 18px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-text-fill: #0ff333; " +
                        "-fx-background-color: #babffa; " +
                        "-fx-padding: 10px; " +
                        "-fx-border-color: #cccccc; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"
        );
        setAlignment(Pos.CENTER);
        setPadding(new javafx.geometry.Insets(70, 0, 0, 0));
        getChildren().add(label);
        getChildren().add(backButton);
    }

    public BackButton getBackButton() {
        return this.backButton.returnBackButton();
    }

}
