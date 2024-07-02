package org.gogroup.command;

import java.util.HashMap;

public class LoadRoomsCommand extends AbstractCommand
{
    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "lobby");
        payload.put("body", new HashMap<String, Object>());
        return this.hashMapToJson(payload);
    }
}
