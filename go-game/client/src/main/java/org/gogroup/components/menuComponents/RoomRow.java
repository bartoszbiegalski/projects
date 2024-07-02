package org.gogroup.components.menuComponents;

import javafx.scene.layout.VBox;

public class RoomRow extends VBox {
    private int playersCount;

    private String gameId;

    public RoomRow(String gameId, int playersCount) {
        this.gameId = gameId;
        this.playersCount = playersCount;
    }
}
