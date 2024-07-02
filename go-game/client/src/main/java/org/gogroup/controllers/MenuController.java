package org.gogroup.controllers;

import org.gogroup.command.NewGameWithBotCommand;
import org.gogroup.command.NewRoomCommand;
import org.gogroup.exceptions.NewGameFailedException;
import org.gogroup.server.Response;
import org.gogroup.windows.*;
import org.gogroup.server.ServerConnector;

import java.io.IOException;

public class MenuController extends AbstractController {
    private final ServerConnector serverConnector;

    public MenuController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void chooseGameMode() throws NewGameFailedException {
        Response response = serverConnector.send(new NewRoomCommand(9));
        if(response.getStatusCode() == 200) {
            this.windowManager.show(GameModeWindow.class);
        } else {
            throw new NewGameFailedException();
        }
    }

    public void playWithBot() throws NewGameFailedException {
        Response response = serverConnector.send(new NewGameWithBotCommand(9));
        if(response.getStatusCode() == 200) {
            this.windowManager.show(GameWindow.class);
        } else {
            throw new NewGameFailedException();
        }
    }

    public void startLobby() throws IOException {
        this.windowManager.show(LobbyWindow.class);
    }

    public void goToLoadGames() throws IOException {
        this.windowManager.show(LoadGameWindow.class);
    }

}
