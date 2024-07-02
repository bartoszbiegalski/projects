package org.gogroup.command;

import java.util.HashMap;

public class JoinRoomCommand extends AbstractCommand
{
    private final String gameId;

    public JoinRoomCommand(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toJSON() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("gameId", this.gameId);

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "join-game");
        payload.put("body", body);

        return this.hashMapToJson(payload);
    }
}
