package org.gogroup.services;

import javafx.scene.paint.Color;
import org.gogroup.components.gameComponents.GameObject;
import org.gogroup.components.gameComponents.boardVBox.Board;
import org.gogroup.components.gameComponents.boardVBox.Field;
import org.gogroup.server.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameObjectParser extends Parser{
    public GameObject parseOk(Response response)
    {
            HashMap<?, ?> game = (HashMap<?, ?>) response.getBody().get("game");

            String clientId = (String) game.get("clientId");
            String opponentId = (String) game.get("guestId");

            HashMap<?, ?> boardObject = (HashMap<?, ?>) game.get("board");
            ArrayList<ArrayList<?>> arrayList = (ArrayList<ArrayList<?>>) boardObject.get("board");

            int size = arrayList.get(0).size();

            Board board = new Board(size, 40, 40); ///TODO: change settings of creating board

            for (int i = 0; i < arrayList.size(); i++) {
                for(int j = 0; j < arrayList.get(i).size(); j++) {
                    String c = (String) arrayList.get(i).get(j);
                    Color color;
                    if(c == null) color = Color.TRANSPARENT;
                    else if (c.equals("G")) color = Color.WHITE;
                    else color = Color.BLACK;
                    board.updateField(j, i, color);
                }
            }
            String currentPlayer = (String) game.get("turn");
            GameObject gameObject = new GameObject(board, clientId, opponentId, currentPlayer, "ok");
            return gameObject;
    }

    public GameObject parseWrong(Response response) {
        HashMap<?, ?> game = (HashMap<?, ?>) response.getBody().get("game");

        String clientId = (String) game.get("clientId");
        String opponentId = (String) game.get("guestId");

        HashMap<?, ?> boardObject = (HashMap<?, ?>) game.get("board");
        ArrayList<ArrayList<?>> arrayList = (ArrayList<ArrayList<?>>) boardObject.get("board");

        int size = arrayList.get(0).size();

        Board board = new Board(size, 40, 40); ///TODO: change settings of creating board

        for (int i = 0; i < arrayList.size(); i++) {
            for(int j = 0; j < arrayList.get(i).size(); j++) {
                String c = (String) arrayList.get(i).get(j);
                Color color;
                if(c == null) color = Color.TRANSPARENT;
                else if (c.equals("G")) color = Color.WHITE;
                else color = Color.BLACK;
                board.updateField(j, i, color);
            }
        }
        String currentPlayer = (String) game.get("turn");
        GameObject gameObject = new GameObject(board, clientId, opponentId, currentPlayer, "wrong");
        return gameObject;
    }

}
