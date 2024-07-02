package org.gogroup.controllers;

import org.gogroup.command.JoinRoomCommand;
import org.gogroup.command.LoadOldGamesCommand;
import org.gogroup.command.LoadRoomsCommand;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.exceptions.JoinRoomException;
import org.gogroup.model.Room;
import org.gogroup.server.Response;
import org.gogroup.server.ServerConnector;
import org.gogroup.services.LoadRoomsParser;
import org.gogroup.windows.GameWindow;
import org.gogroup.windows.MenuWindow;
import org.gogroup.windows.PreviousGameWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LoadGameWindowController extends AbstractController{
    private ServerConnector serverConnector;

    public LoadGameWindowController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void joinRoom(String gameId) throws JoinRoomException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("gameId", gameId);
        this.windowManager.show(PreviousGameWindow.class, parameters);
    }


    public ArrayList<Room> loadGames() {
        Response response = this.serverConnector.send(new LoadOldGamesCommand());
        if (!(response.getBody().get("games") instanceof ArrayList<?>)) {
            return new ArrayList<Room>();
        }
            LoadRoomsParser loadRoomsParser = new LoadRoomsParser(response);
            return loadRoomsParser.getGames();
    }

    public void returnFromLoadGame() {
            this.windowManager.show(MenuWindow.class);
    }
}
