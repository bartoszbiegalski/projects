package org.gogroup.controllers;

import org.gogroup.command.NewRoomCommand;
import org.gogroup.exceptions.NewGameFailedException;
import org.gogroup.server.Response;
import org.gogroup.server.ServerConnector;
import org.gogroup.windows.GameModeWindow;
import org.gogroup.windows.GameWindow;

public class GameModeController extends AbstractController{
    private final ServerConnector serverConnector;

    public GameModeController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void start(int size) throws NewGameFailedException
    {
        Response response = serverConnector.send(new NewRoomCommand(size));
        if(response.getStatusCode() == 200) {
            this.windowManager.show(GameWindow.class);
        } else {
            throw new NewGameFailedException();
        }
    }
}
