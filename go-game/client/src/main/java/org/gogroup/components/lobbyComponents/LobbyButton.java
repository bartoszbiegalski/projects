package org.gogroup.components.lobbyComponents;

import javafx.scene.control.Button;

public class LobbyButton extends Button {

    public LobbyButton(String name)
    {
        super(name);
        setPrefWidth(200);
        setPrefHeight(70);
        setStyle("-fx-background-color: #00fff0;");
    }

    public Button getLobbyButton() {
        return this;
    }

}
