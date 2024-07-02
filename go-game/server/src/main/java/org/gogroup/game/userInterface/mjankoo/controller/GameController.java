package org.gogroup.game.userInterface.mjankoo.controller;

import org.gogroup.game.application.LoadGame;
import org.gogroup.game.application.Move;
import org.gogroup.game.application.SkipTurn;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.game.domain.exception.InvalidMoveException;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.requestHandler.AbstractController;
import org.mjankoo.framework.requestHandler.Request.Request;
import org.mjankoo.framework.requestHandler.Response.Response;
import org.mjankoo.framework.requestHandler.Route;

public class GameController extends AbstractController
{
    private final LoadGame loadGame;

    private final Move move;

    private final SkipTurn skipTurn;

    public GameController(LoadGame loadGame, Move move, SkipTurn skipTurn)
    {
        this.loadGame = loadGame;
        this.move = move;
        this.skipTurn = skipTurn;
    }

    @Route(url = "move")
    public Response move(Request request)
    {
        Object x = request.getBody().get("x");
        Object y = request.getBody().get("y");
        if(!(x instanceof Integer)) {
            Response response = new Response(400);
            response.addValue("message", "you didin't provide x value");
            return response;
        }
        if(!(y instanceof Integer)) {
            Response response = new Response(400);
            response.addValue("message", "you didin't provide y value");
            return response;
        }

        GameModel gameModel;
        try {
            gameModel = this.move.execute(request.getClientId(), (int) x, (int) y);
        } catch (GameDoesntExistsException | GameRuleException e) {
            Response response = new Response(400);
            response.addValue("message", e.getMessage());
            return response;
        }

        Response response = new Response(200);
        response.addValue("game", gameModel);
        return response;
    }

    @Route(url = "load-game-object")
    public Response loadGameObject(Request request)
    {
        GameModel gameModel;
        try {
            gameModel = this.loadGame.execute(request.getClientId());
        } catch (GameDoesntExistsException e) {
            Response response = new Response(404);
            response.addValue("message", e.getMessage());
            return response;
        }

        Response response = new Response(200);
        response.addValue("game", gameModel);
        return response;
    }

    @Route(url = "surrender")
    public Response surrender(Request request)
    {
        return new Response(200);
    }

    @Route(url = "skip-turn")
    public Response skipTurn(Request request) {
        try {
            this.skipTurn.execute(request.getClientId());
        } catch (GameDoesntExistsException| InvalidMoveException e) {
            Response response = new Response(400);
            response.addValue("message", e.getMessage());
            return response;
        }
        return new Response(200);
    }
}
