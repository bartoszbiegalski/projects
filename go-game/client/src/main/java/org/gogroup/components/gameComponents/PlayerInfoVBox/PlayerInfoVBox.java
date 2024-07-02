package org.gogroup.components.gameComponents.PlayerInfoVBox;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import org.gogroup.components.gameComponents.VBoxInterface;

public class PlayerInfoVBox extends VBox implements VBoxInterface {

    public PlayerInfoVBox()
    {
       setAlignment(Pos.CENTER_LEFT);
    }

    public void add(Node node)
    {
        getChildren().add(node);
    }

}
