package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class ListRooms
{
    private final GameRegistry gameRegistry;

    public ListRooms(GameRegistry gameRegistry) {
        this.gameRegistry = gameRegistry;
    }

    public ArrayList<Room> execute()
    {
        HashMap<String, Game> games = this.gameRegistry.getGames();
        ArrayList<Room> rooms = new ArrayList<>();
        games.forEach((key, game) -> {
            rooms.add(Room.fromGame(game));
        });
        return rooms;
    }
}
