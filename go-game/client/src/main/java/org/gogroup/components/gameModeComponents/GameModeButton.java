package org.gogroup.components.gameModeComponents;

import javafx.scene.control.Button;
import org.gogroup.components.BackButton;

public class GameModeButton extends Button {

    public GameModeButton(String name)
    {
        super(name);
        setWidth(200);
        setHeight(100);
        setStyle("-fx-background-color: #0123a0;");
    }
    public GameModeButton returnGameModeButton() {
        return this;
    }

}
