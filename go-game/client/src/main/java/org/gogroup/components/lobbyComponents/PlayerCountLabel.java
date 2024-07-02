package org.gogroup.components.lobbyComponents;

import javafx.scene.control.Label;
import org.gogroup.model.Room;

public class PlayerCountLabel extends Label {

    public PlayerCountLabel()
    {
        super("Player count: ");
    }
    public void setPlayerCount(Room room)
    {
        setText("Player count: " + room.getPlayersCount() + "/2");
    }
}
