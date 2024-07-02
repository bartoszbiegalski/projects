package org.gogroup.components.gameComponents;

import javafx.scene.Group;
import org.gogroup.components.gameComponents.boardVBox.Board;
import org.gogroup.components.gameComponents.boardVBox.FieldGroup;

public class GameObject {
    private Board board;
    private String clientId;
    private String opponentId;
    private String currentPlayer;
    private String isWrongMove;


    public GameObject(Board board, String clientId, String opponentId, String currentPlayer, String isWrongMove) {
        this.board = board;
        this.clientId = clientId;
        this.opponentId = opponentId;
        this.currentPlayer = currentPlayer;
        this.isWrongMove = isWrongMove;
    }

    public GameObject() {

    }
    public String getIsWrongMove() { return isWrongMove; }

    public String getCurrentPlayer() { return currentPlayer; }

    public Board getBoard() {
        return this.board;
    }

    public String getClientId() { return this.clientId; }

    public String getOpponentId() { return this.opponentId; }


}
