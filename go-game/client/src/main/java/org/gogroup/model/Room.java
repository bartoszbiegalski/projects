package org.gogroup.model;

public class Room {
    private int playersCount;

    private String gameId;

    public Room(String gameId, int playersCount) {
        this.gameId = gameId;
        this.playersCount = playersCount ;
    }

    public String getGameId() {
        return this.gameId;
    }

    public int getPlayersCount()
    {
        return playersCount;
    }
}
