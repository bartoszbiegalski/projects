package org.gogroup.gameRetrospective.userInterface.mjankoo.controller;

import org.gogroup.gameRetrospective.application.GetPreviousGames;
import org.mjankoo.framework.requestHandler.AbstractController;
import org.mjankoo.framework.requestHandler.Request.Request;
import org.mjankoo.framework.requestHandler.Response.Response;
import org.mjankoo.framework.requestHandler.Route;

public class GameController extends AbstractController
{
    private GetPreviousGames getPreviousGames;
    public GameController(GetPreviousGames getPreviousGames) {
        this.getPreviousGames = getPreviousGames;
    }

    @Route(url = "previous-games")
    public Response getPreviousGames(Request request) {
        Response response = new Response(200);
        response.addValue("games", getPreviousGames.execute());
        return response;
    }
}
