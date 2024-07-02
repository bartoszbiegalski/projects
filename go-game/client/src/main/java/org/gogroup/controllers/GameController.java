package org.gogroup.controllers;

import javafx.scene.paint.Color;
import org.gogroup.command.*;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.components.gameComponents.boardVBox.Board;
import org.gogroup.exceptions.MoveException;
import org.gogroup.exceptions.SurrenderException;
import org.gogroup.model.Room;
import org.gogroup.server.ServerConnector;
import org.gogroup.server.Response;
import org.gogroup.services.GameObjectParser;
import org.gogroup.windows.EndGameWindow;
import org.gogroup.windows.GameModeWindow;
import org.gogroup.windows.GameWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameController extends AbstractController{
    protected ServerConnector serverConnector;

    public GameController(ServerConnector serverConnector)
    {
        this.serverConnector = serverConnector;
    }

    public GameObject loadGameObject()
    {
        Response response = this.serverConnector.send(new LoadGameObjectCommand());
        if (!(response.getBody().get("game") instanceof HashMap)) {
            return new GameObject();
        }
        GameObjectParser gameObjectParser = new GameObjectParser();
        return gameObjectParser.parseOk(response);
    }


    public GameObject move(int posX, int posY, GameObject gameObject)
    {
       Response response = serverConnector.send(new MoveCommand(posX, posY));
       if (response.getStatusCode() == 200) {
             GameObjectParser gameObjectParser = new GameObjectParser();
             return gameObjectParser.parseOk(response);
       }
       return gameObject;
    }

    public void surrender() throws SurrenderException {
        //Response response = serverConnector.send(new SurrenderCommand());
        System.out.println("surrender");
        this.windowManager.show(EndGameWindow.class);

    }

    public void skipTurn() throws SkipTurnException {
        Response response = serverConnector.send(new SkipTurnCommand());
        this.show();
    }

    public void show() {
        this.windowManager.show(GameWindow.class);
    }

    public void finishGame() {
        this.windowManager.show(EndGameWindow.class);
    }
}
