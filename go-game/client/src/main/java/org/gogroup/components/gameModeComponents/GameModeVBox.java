package org.gogroup.components.gameModeComponents;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class GameModeVBox extends VBox {

    public GameModeVBox(int size)
    {
        super(size);
        setAlignment(Pos.CENTER);
    }

    public GameModeVBox getGameModeVBox()
    {
        return this;
    }

}
