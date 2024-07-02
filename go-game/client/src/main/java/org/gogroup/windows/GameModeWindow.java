package org.gogroup.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.gogroup.components.gameModeComponents.GameModeButton;
import org.gogroup.components.gameModeComponents.GameModeVBox;
import org.gogroup.controllers.GameModeController;
import org.gogroup.exceptions.NewGameFailedException;

import java.util.HashMap;

public class GameModeWindow extends AbstractWindow {
    private GameModeController gameModeController;
    private GameModeVBox gameModeVBox;
    private GameModeButton button1;
    private GameModeButton button2;
    private GameModeButton button3;

    public GameModeWindow(GameModeController gameModeController) {this.gameModeController = gameModeController;}

    public void setGameModeVBox()
    {
        this.button1 = new GameModeButton("9x9");
        setButtonOnClick(button1, 9);

        this.button2 = new GameModeButton("13x13");
        setButtonOnClick(button2, 13);

        this.button3 = new GameModeButton("19x19");
        setButtonOnClick(button3, 19);

        this.gameModeVBox = new GameModeVBox(100);
        this.gameModeVBox.getChildren().add(this.button1);
        this.gameModeVBox.getChildren().add(this.button2);
        this.gameModeVBox.getChildren().add(this.button3);

    }

    private void setButtonOnClick(GameModeButton gameModeButton, int size)
    {
        gameModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    gameModeController.start(size);
                } catch (NewGameFailedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public Scene getScene(HashMap<String, Object> parameters) {

        setGameModeVBox();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(gameModeVBox);

        return new Scene(root, 1000, 700);
    }
}
