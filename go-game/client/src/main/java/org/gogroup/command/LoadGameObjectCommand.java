package org.gogroup.command;

import org.gogroup.components.gameComponents.GameObject;

import java.util.HashMap;

public class LoadGameObjectCommand extends AbstractCommand{

    @Override
    public String toJSON() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "load-game-object");
        payload.put("body", new HashMap<String, Object>());
        return this.hashMapToJson(payload);
    }
}
