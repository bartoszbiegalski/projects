package org.gogroup.controllers;

import org.gogroup.command.SkipTurnCommand;
import org.gogroup.server.Response;
import org.gogroup.server.ServerConnector;
import org.gogroup.services.GameResultParser;
import org.gogroup.windows.MenuWindow;

public class EndGameWindowController extends AbstractController{
    private ServerConnector serverConnector;

    public EndGameWindowController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public String getGameResult() {
        Response response = this.serverConnector.send(new SkipTurnCommand());
        GameResultParser gameResultParser = new GameResultParser();
        return gameResultParser.getGameResult(response);
    }

    public void returnToMenu() {
        this.windowManager.show(MenuWindow.class);
    }

}
