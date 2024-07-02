package org.gogroup.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.gogroup.components.endGameComponents.EndGameVBox;
import org.gogroup.controllers.EndGameWindowController;

import java.util.HashMap;


public class EndGameWindow extends AbstractWindow {
    private EndGameWindowController endGameWindowController;
    private EndGameVBox endGameVBox;

    public EndGameWindow(EndGameWindowController endGameWindowController) {
        this.endGameWindowController = endGameWindowController;
    }

    public void setBackButton()
    {
        System.out.println(endGameVBox.getBackButton());
        this.endGameVBox.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    endGameWindowController.returnToMenu();
            }
        });
    }




    @Override
    public Scene getScene(HashMap<String, Object> parameters) {
        this.endGameVBox = new EndGameVBox("Game ended");
        this.setBackButton();
        return new Scene(this.endGameVBox, 800, 600);
    }


}
