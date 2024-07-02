package org.gogroup.game.domain;

public class Room {
    private String gameId;

    private int playersCount;

    private int size;

    private Room() {
    }

    public static Room fromGame(Game game) {
        Room room = new Room();
        room.gameId = game.getId();
        room.playersCount = game.getPlayersCount();
        //room.size = game.getGameModel();

        return room;
    }

    public String getGameId() {
        return this.gameId;
    }

    public int getPlayersCount() {
        return this.playersCount;
    }

    public int size() {
        return this.size;
    }
}
