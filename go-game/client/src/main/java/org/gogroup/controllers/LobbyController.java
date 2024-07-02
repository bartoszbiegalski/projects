package org.gogroup.controllers;

import org.gogroup.command.JoinRoomCommand;
import org.gogroup.command.LoadRoomsCommand;
import org.gogroup.exceptions.JoinRoomException;
import org.gogroup.exceptions.NewGameFailedException;
import org.gogroup.model.Room;
import org.gogroup.server.Response;
import org.gogroup.server.ServerConnector;
import org.gogroup.windows.GameModeWindow;
import org.gogroup.windows.GameWindow;
import org.gogroup.windows.MenuWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class LobbyController extends AbstractController{
    private final ServerConnector serverConnector;

    public LobbyController(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public ArrayList<Room> loadRooms() {
        Response response = this.serverConnector.send(new LoadRoomsCommand());
        if (!(response.getBody().get("rooms") instanceof ArrayList<?>)) {
            return new ArrayList<>();
        }
        ArrayList<?> rooms = (ArrayList<?>) response.getBody().get("rooms");
        ArrayList<Room> processedRooms = new ArrayList<>();

        rooms.forEach(room -> {
            if (!(room instanceof LinkedHashMap)) {
                return;
            }
            Object playersCount = ((LinkedHashMap<?, ?>) room).getOrDefault("playersCount", null);
            Object gameId = ((LinkedHashMap<?, ?>) room).getOrDefault("gameId", null);

            if (!(playersCount instanceof Integer) || !(gameId instanceof String)) {
                return;
            }

            processedRooms.add(
                    new Room((String) gameId, (Integer) playersCount)
            );
        });

        return processedRooms;
    }

    public void createNewRoom() throws NewGameFailedException {
        this.windowManager.show(GameModeWindow.class);
    }

    public void joinRoom(String gameId) throws JoinRoomException {
        Response response = serverConnector.send(new JoinRoomCommand(gameId));
        if(response.getStatusCode() == 200) {
            this.windowManager.show(GameWindow.class);
        } else {
            throw new JoinRoomException();
        }
    }

    public void returnFromLobby() throws IOException {
        this.windowManager.show(MenuWindow.class);
    }
}
