package org.gogroup.services;

import org.gogroup.model.Room;
import org.gogroup.server.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadRoomsParser extends Parser{
    private Response response;

    public LoadRoomsParser(Response response) {
        this.response = response;
    }

    public ArrayList<Room> getGames() {
        ArrayList<String> keys = (ArrayList<String>) this.response.getBody().get("games");
        ArrayList<Room>  rooms = new ArrayList<>();
        keys.forEach(room -> {
            System.out.println(room);
            rooms.add(new Room(room, 2));
        });
        return rooms;
    }

}
