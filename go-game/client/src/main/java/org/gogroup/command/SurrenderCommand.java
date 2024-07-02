package org.gogroup.command;

import java.util.HashMap;

public class SurrenderCommand extends AbstractCommand{
    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "surrender");
        payload.put("body", new HashMap<String, Object>());
        return this.hashMapToJson(payload);
    }
}
