package org.gogroup.shared.readModel;

import org.gogroup.game.domain.GameStatus;

public class GameModel {
    private final String gameId;

    private final String clientId;

    private final String hostId;

    private final String guestId;

    private final BoardModel board;

    private final String turn;

    private final GameStatus gameStatus;

    public GameModel(
            String gameId,
            String clientId,
            String hostId,
            String opponentId,
            String playerWhoHasTurnId,
            String[][] fields,
            GameStatus gameStatus
    )  {
        this.gameId = gameId;
        this.clientId = clientId;
        this.hostId = hostId;
        this.guestId = opponentId;
        this.board = new BoardModel(hostId, fields);
        this.gameStatus = gameStatus;

        if (clientId.equals(playerWhoHasTurnId)) {
            this.turn = "Y";
        } else {
            this.turn = "O";
        }
    }

    public String getGameId() {
        return gameId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getHostId() {
        return this.hostId;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public BoardModel getBoard() {
        return this.board;
    }

    public String getTurn() {
        return this.turn;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
