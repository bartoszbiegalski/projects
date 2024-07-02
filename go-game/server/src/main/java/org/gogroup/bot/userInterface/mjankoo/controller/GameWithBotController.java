package org.gogroup.bot.userInterface.mjankoo.controller;

import org.gogroup.bot.application.AddBotToGame;
import org.gogroup.game.application.CreateGame;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.mjankoo.framework.requestHandler.AbstractController;
import org.mjankoo.framework.requestHandler.Request.Request;
import org.mjankoo.framework.requestHandler.Response.Response;
import org.mjankoo.framework.requestHandler.Route;

public class GameWithBotController extends AbstractController {
    private CreateGame createGame;
    private AddBotToGame addBotToGame;


    public GameWithBotController(CreateGame createGame, AddBotToGame addBotToGame) {
        this.createGame = createGame;
        this.addBotToGame = addBotToGame;
    }


    @Route(url = "create-game-bot")
    public Response createGameWithBot(Request request) {
        Object size = request.getBody().getOrDefault("size", null);
        if (!(size instanceof Integer)) {
            Response response = new Response(400);
            response.addValue("message", "Invalid size value.");
            return response;
        }

        try {
            this.createGame.execute((Integer) size, request.getClientId());
            this.addBotToGame.execute(request.getClientId());
        } catch (CannotCreateNewGameException | GameDoesntExistsException | CannotJoinRoomException e) {
            Response response = new Response(400);
            response.addValue("message", e.getMessage());
            return response;
        }
        return new Response(200);
    }
}
