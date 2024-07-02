package org.gogroup.game.userInterface.mjankoo.controller;

import org.gogroup.game.application.CreateGame;
import org.gogroup.game.application.JoinRoom;
import org.gogroup.game.application.ListRooms;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.mjankoo.framework.requestHandler.AbstractController;
import org.mjankoo.framework.requestHandler.Request.Request;
import org.mjankoo.framework.requestHandler.Response.Response;
import org.mjankoo.framework.requestHandler.Route;

public class LobbyController extends AbstractController
{
    private final ListRooms listRooms;

    private final CreateGame createGame;

    private final JoinRoom joinRoom;

    public LobbyController(ListRooms listRooms, CreateGame createGame, JoinRoom joinRoom) {
        this.listRooms = listRooms;
        this.createGame = createGame;
        this.joinRoom = joinRoom;
    }

    @Route(url = "lobby")
    public Response getGames(Request request)
    {
        Response response = new Response(200);
        response.addValue("rooms", this.listRooms.execute());
        return response;
    }

    @Route(url = "create-game")
    public Response createGame(Request request)
    {
        Object size = request.getBody().getOrDefault("size", null);
        if (!(size instanceof Integer)) {
            Response response = new Response(400);
            response.addValue("message", "Invalid size value.");
            return response;
        }

        try {
            this.createGame.execute((Integer) size, request.getClientId());
        } catch (CannotCreateNewGameException e) {
            Response response = new Response(400);
            response.addValue("message", e.getMessage());
            return response;
        }

        return new Response(200);
    }

    @Route(url = "join-game")
    public Response joinGame(Request request)
    {
        Object gameId = request.getBody().getOrDefault("gameId", null);
        if (!(gameId instanceof String)) {
            Response response = new Response(400);
            response.addValue("message", "Invalid game id value.");
            return response;
        }

        try {
            this.joinRoom.execute((String) gameId, request.getClientId());
        } catch (GameDoesntExistsException e) {
            Response response = new Response(404);
            response.addValue("message", e.getMessage());
            return response;
        } catch (CannotJoinRoomException e) {
            Response response = new Response(400);
            response.addValue("message", e.getMessage());

            return response;
        }

        return new Response(200);
    }
}
