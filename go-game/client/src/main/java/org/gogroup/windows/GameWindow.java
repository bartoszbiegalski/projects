package org.gogroup.windows;

import javafx.application.Platform;
import javafx.scene.Scene;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.components.gameComponents.boardVBox.BoardVBox;
import org.gogroup.components.gameComponents.bottomVBox.BottomHBox;
import org.gogroup.controllers.GameController;
import org.gogroup.controllers.SkipTurnException;
import org.gogroup.event.BoardChangedEvent;
import org.gogroup.event.GameFinishedEvent;
import org.gogroup.exceptions.SurrenderException;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class GameWindow extends AbstractWindow implements EventSubscriberInterface {
    private GameController gameController;
    protected BoardVBox boardVBox;
    protected BottomHBox bottomHBox;

    public GameWindow(GameController gameController) {
        this.gameController = gameController;
    }

    public void setPlayerLabel(GameObject gameObject) {
        if (Objects.equals(gameObject.getOpponentId(), null)) {
            bottomHBox.setPlayerVBox("Waiting for another player");
        }
        else if (Objects.equals(gameObject.getCurrentPlayer(), "Y")) {
            bottomHBox.setPlayerVBox("Now it's your turn");
        }
        else {
            bottomHBox.setPlayerVBox("Now it's your opponent turn");
        }
    }

    public void setSurrenderButton() {
        this.bottomHBox.getButtonVBox().getSurrenderButton().setOnAction(actionEvent -> {
            try {
                this.gameController.surrender();
            } catch (SurrenderException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setSkipTurnButton() {
        this.bottomHBox.getButtonVBox().getSkipButton().setOnAction(actionEvent -> {
            try {
                System.out.println("skip?");
                this.gameController.skipTurn();
            } catch (SkipTurnException e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected void setBoardVBox(GameObject gameObject) {
        this.boardVBox = new BoardVBox();
        this.boardVBox.add(gameObject.getBoard().getGroup());
        this.boardVBox.add(bottomHBox);
    }

    private void setErrorLabel(GameObject gameObject) {
        /*
        if(gameObject.getIsWrongMove() == "wrong") {
            this.boardVBox.setErrorLabel(false);

        }
        else {
            this.boardVBox.setErrorLabel(true);
        }

         */
    }

    protected void setBottomHBoxWithoutButtons() {
        this.bottomHBox = new BottomHBox("");
        bottomHBox.getPlayerVBox().getPlayerLabel().setVisible(false);
        bottomHBox.getButtonVBox().getSkipButton().setVisible(false);
        bottomHBox.getButtonVBox().getSurrenderButton().setVisible(false);

    }

    protected void setBottomHBox() {
        this.bottomHBox = new BottomHBox("Player 1");
        setSurrenderButton();
        setSkipTurnButton();
    }

    protected void setPawnClicked(GameObject gameObject)
    {
        for (int i = 0; i < gameObject.getBoard().getSize(); i++) {
            for(int j = 0; j < gameObject.getBoard().getSize(); j++) {
                int finalI = i;
                int finalJ = j;
                gameObject.getBoard().getGroup().findField(finalI, finalJ).setOnMouseClicked(mouseEvent -> {
                    gameController.move(finalI, finalJ, gameObject);
                    gameController.show();
                });
            }
        }
    }

    public void reloadBoard(BoardChangedEvent event)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameController.show();
            }
        });
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(BoardChangedEvent.class, new ArrayList<>(
                    Arrays.asList("reloadBoard", 0)
            ));
        }};
    }

    @Override
    public Scene getScene(HashMap<String, Object> parameters) {
        GameObject gameObject = this.gameController.loadGameObject();
        this.setPawnClicked(gameObject);
        this.setBottomHBox();
        this.setPlayerLabel(gameObject);
        this.setBoardVBox(gameObject);
        this.setErrorLabel(gameObject);
        return new Scene(this.boardVBox, 1200, 800);
    }
}
