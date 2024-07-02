package org.gogroup.command;

import org.mjankoo.framework.requestHandler.AbstractController;

import java.util.HashMap;

public class LoadOldGamesCommand extends AbstractCommand {
    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "previous-games");
        payload.put("body", new HashMap<String, Object>());
        return this.hashMapToJson(payload);
    }
}
