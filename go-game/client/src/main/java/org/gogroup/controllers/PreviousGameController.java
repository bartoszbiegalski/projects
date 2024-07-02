package org.gogroup.controllers;

import org.gogroup.command.LoadPreviousGameObjectCommand;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.server.Response;
import org.gogroup.server.ServerConnector;
import org.gogroup.services.GameObjectParser;
import org.gogroup.windows.PreviousGameWindow;

import java.util.HashMap;

public class PreviousGameController extends GameController{
    public PreviousGameController(ServerConnector serverConnector) {
        super(serverConnector);
    }

    public GameObject loadGameObject(Object object, Integer moveId)
    {
        Response response = this.serverConnector.send(new LoadPreviousGameObjectCommand((String) object, moveId));
        if (!(response.getBody().get("game") instanceof HashMap)) {
            return new GameObject();
        }
        GameObjectParser gameObjectParser = new GameObjectParser();
        return gameObjectParser.parseOk(response);
    }

    public void reload(HashMap<String, Object> parameters) {
        this.windowManager.show(PreviousGameWindow.class, parameters);
    }
}
