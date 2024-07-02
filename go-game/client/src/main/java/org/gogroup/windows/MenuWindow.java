package org.gogroup.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import org.gogroup.components.menuComponents.MenuButton;
import org.gogroup.controllers.MenuController;
import org.gogroup.exceptions.NewGameFailedException;

import java.io.IOException;
import java.util.HashMap;


public class MenuWindow extends AbstractWindow {
    private MenuController menuController;

    public MenuWindow(MenuController menuController) {
       this.menuController = menuController;
    }

    @Override
    public Scene getScene(HashMap<String, Object> parameters) {
        VBox root = new VBox();

        VBox titleVBox = new VBox();
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setPrefHeight(200);
        Label label = new Label("GO GAME");
        label.setStyle("-fx-font-size: 20px;"); // Ustawianie rozmiaru tekstu
        titleVBox.getChildren().add(label);

        VBox choiceVBox = new VBox();
        choiceVBox.setAlignment(Pos.CENTER);
        choiceVBox.setSpacing(100);

        MenuButton menuButton1 = new MenuButton("Single player");

        MenuButton menuButton2 = new MenuButton("Multi player");

        MenuButton menuButton3 = new MenuButton("See previous games");

        choiceVBox.getChildren().add(menuButton1);
        choiceVBox.getChildren().add(menuButton2);
        choiceVBox.getChildren().add(menuButton3);

        root.getChildren().add(titleVBox);
        root.getChildren().add(choiceVBox);
        Scene scene = new Scene(root, 1000, 700);

        menuButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    menuController.playWithBot();
                } catch (NewGameFailedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        menuButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    menuController.startLobby();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        menuButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    menuController.goToLoadGames();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            double newWidth = (double) newValue;
            root.setPrefWidth(newWidth);  // Ustawienie preferowanej szerokości VBox na szerokość sceny
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double newHeight = (double) newValue;
            root.setPrefWidth(newHeight);  // Ustawienie preferowanej szerokości VBox na szerokość sceny
        });
        return scene;
    }
}
