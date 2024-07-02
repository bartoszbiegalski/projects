package org.gogroup.components.gameComponents.boardVBox;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.gogroup.components.gameComponents.VBoxInterface;

public class BoardVBox extends VBox implements VBoxInterface {

    private Label errorLabel;

    public BoardVBox()
    {
        this.errorLabel = new Label("Opponent's turn!");
        this.errorLabel.setVisible(false);
        setAlignment(Pos.CENTER);
        errorLabel.setStyle(
                "-fx-background-color: black; " +
                        "-fx-text-fill: yellow; " +
                        "-fx-font-size: 14px; " +
                        "-fx-border-color: white; " +
                        "-fx-border-width: 2px; " +
                        "-fx-background-insets: 0,1,2,3;" +
                        "-fx-background-radius: 3,2,2,2;" +
                        "-fx-padding: 3 30 3 30;"
        );
        getChildren().add(errorLabel);
    }

    public void add(Node node)
    {
        getChildren().add(node);
    }


    public void setErrorLabel(boolean condition) {
        errorLabel.setVisible(condition);
    }

}
