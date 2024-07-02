package org.gogroup.gameRetrospective.userInterface.mjankoo.controller;

import org.gogroup.gameRetrospective.application.GetBoardState;
import org.gogroup.gameRetrospective.domain.exception.NoMoreMovesException;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.requestHandler.AbstractController;
import org.mjankoo.framework.requestHandler.Request.Request;
import org.mjankoo.framework.requestHandler.Response.Response;
import org.mjankoo.framework.requestHandler.Route;

public class BoardStateController extends AbstractController
{
    private GetBoardState getBoardState;

    public BoardStateController(GetBoardState getBoardState)
    {
        this.getBoardState = getBoardState;
    }

    @Route(url = "board-state")
    public Response getBoardState(Request request)
    {
        Object moveId = request.getBody().get("moveId");
        if (!(moveId instanceof Integer)) {
            Response response = new Response(400);
            response.addValue("message", "Invalid move id.");
            return response;
        }

        Object gameId = request.getBody().get("gameId");
        if (!(gameId instanceof String)) {
            Response response = new Response(400);
            response.addValue("message", "Invalid game id.");
            return response;
        }

        GameModel gameModel;
        try {
            gameModel = this.getBoardState.execute((String) gameId, request.getClientId(), (Integer) moveId);
        } catch (NoMoreMovesException exception) {
            Response response = new Response(400);
            response.addValue("message", exception.getMessage());
            return response;
        }

        Response response = new Response(200);
        response.addValue("game", gameModel);
        return response;
    }
}
