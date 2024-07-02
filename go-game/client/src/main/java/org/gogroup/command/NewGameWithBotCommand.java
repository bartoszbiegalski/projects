package org.gogroup.command;

import java.util.HashMap;

public class NewGameWithBotCommand extends AbstractCommand {
    private Integer size;

    public NewGameWithBotCommand(Integer size) {
        this.size = size;
    }

    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "create-game-bot");
        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("size", size);
        payload.put("body", body);
        return this.hashMapToJson(payload);
    }
}
