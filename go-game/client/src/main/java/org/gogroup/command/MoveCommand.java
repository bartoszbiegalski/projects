package org.gogroup.command;

import javafx.util.Pair;

import java.util.HashMap;

public class MoveCommand extends AbstractCommand {

    private int posX;
    private int posY;

    public MoveCommand(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }


    @Override
    public String toJSON() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("x", this.posX);
        body.put("y", this.posY);

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("route", "move");
        payload.put("body", body);

        return this.hashMapToJson(payload);
    }
}
