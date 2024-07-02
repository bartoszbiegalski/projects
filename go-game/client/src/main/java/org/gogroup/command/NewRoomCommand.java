package org.gogroup.command;

public class NewRoomCommand extends AbstractCommand{
    int size;

    public NewRoomCommand(int size) {
        this.size = size;
    }

    @Override
    public String toJSON()
    {
        return "{\"route\": \"create-game\", \"body\": {\"size\":" + String.valueOf(size) + "}}";
    }
}
