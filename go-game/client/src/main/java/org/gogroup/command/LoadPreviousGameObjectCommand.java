package org.gogroup.command;

import java.util.HashMap;
import java.util.Objects;

public class LoadPreviousGameObjectCommand extends AbstractCommand{
    private String gameId;
    private Integer moveId;

    public LoadPreviousGameObjectCommand(Object object, Integer moveId) {
        this.gameId = (String) object;
        this.moveId = moveId;
    }

    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "board-state");
        HashMap<String, Object> body = new HashMap<>();
        body.put("gameId", this.gameId);
        body.put("moveId", this.moveId);
        payload.put("body", body);
        return this.hashMapToJson(payload);
    }
}
