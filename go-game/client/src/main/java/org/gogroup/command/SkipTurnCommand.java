package org.gogroup.command;

import java.util.HashMap;

public class SkipTurnCommand extends AbstractCommand {
    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "skip-turn");
        payload.put("body", new HashMap<String, Object>());
        return this.hashMapToJson(payload);
    }
}
