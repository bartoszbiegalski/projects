package org.gogroup.components.menuComponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.gogroup.exceptions.NewGameFailedException;

public class MenuButton extends Button
{
    public MenuButton(String buttonName) {
        super(buttonName);
        setPrefWidth(200);
        setPrefHeight(80);
        setStyle("-fx-background-color: #00ff00;");
        setAlignment(Pos.CENTER);
    }

    public Button getMenuButton() {
        return this;
    }

}
