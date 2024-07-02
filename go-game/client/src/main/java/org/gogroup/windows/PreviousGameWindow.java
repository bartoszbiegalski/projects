package org.gogroup.windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.components.gameComponents.bottomVBox.BottomHBox;
import org.gogroup.components.previousGameButtons.PreviousButton;
import org.gogroup.controllers.PreviousGameController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class PreviousGameWindow extends GameWindow{
    private PreviousGameController previousGameController;
    private HashMap<String, Object> parameters;
    private PreviousButton leftButton;
    private PreviousButton rightButton;

    public PreviousGameWindow(PreviousGameController previousGameController) {
        super(previousGameController);
        this.previousGameController = previousGameController;
    }

    public void show(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    protected void setBottomHBox() {
        this.bottomHBox = new BottomHBox("");
        this.bottomHBox.setAlignment(Pos.CENTER);
        this.bottomHBox.getChildren().clear();
        this.bottomHBox.getChildren().add(leftButton);
        this.bottomHBox.getChildren().add(rightButton);
    }

    @Override
    public Scene getScene(HashMap<String, Object> parameters) {
        String gameId = (String) parameters.get("gameId");
        AtomicReference<Integer> moveId = new AtomicReference<>((Integer) parameters.getOrDefault("moveId", 0));
        GameObject gameObject = this.previousGameController.loadGameObject(gameId, moveId.get());
        leftButton = new PreviousButton("Previous move");
        rightButton = new PreviousButton("Next move");
        leftButton.setOnMouseClicked(mouseEvent->  {
            if (moveId.get() == 0) {
                return;
            }
            moveId.getAndSet(moveId.get() - 1);
            parameters.put("moveId", moveId.get());
            this.previousGameController.reload(parameters);
        });
        rightButton.setOnMouseClicked(mouseEvent->  {
            moveId.getAndSet(moveId.get() + 1);
            parameters.put("moveId", moveId.get());
            this.previousGameController.reload(parameters);
        });
        this.setBottomHBox();
        this.setBoardVBox(gameObject);


        return new Scene(this.boardVBox, 1200, 800);
    }
}
