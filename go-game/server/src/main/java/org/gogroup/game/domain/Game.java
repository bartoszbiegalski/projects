package org.gogroup.game.domain;

import org.gogroup.game.domain.event.ClientJoinedGameEvent;
import org.gogroup.game.domain.event.ClientMovedEvent;
import org.gogroup.game.domain.event.ClientSkippedTurnEvent;
import org.gogroup.game.domain.event.GameFinishedEvent;
import org.gogroup.game.domain.exception.*;
import org.gogroup.game.domain.gameRule.GameRule;
import org.gogroup.shared.readModel.GameModel;
import org.gogroup.shared.AbstractAggregate;

import java.util.UUID;

public class Game extends AbstractAggregate
{
    private String id;

    private Board board;

    private GameStatus gameStatus;

    private String hostId;

    private int hostPoints = 0;

    private String guestId;

    private int guestPoints = 0;

    private String turn;

    private GameRule gameRule;

    private String winnerId;

    private boolean hostSkippedLastTurn = false;
    private boolean guestSkippedLastTurn = false;

    private Game() {
    }

    public static Game createFromClientId(int size, String clientId, GameRegistry gameRegistry, GameRule gameRule) throws CannotCreateNewGameException
    {
        if (!Game.canClientCreateGame(clientId, gameRegistry)) {
            throw CannotCreateNewGameException.becauseUserHasRunningGame();
        }

        Game game = new Game();
        game.id = UUID.randomUUID().toString();
        game.hostId = clientId;
        game.gameStatus = GameStatus.WAITING;
        game.board = new Board(size);
        game.gameRule = gameRule;

        gameRegistry.add(game);

        return game;
    }

    public GameModel getGameModel(String clientId)
    {
        return new GameModel(this.id, clientId, this.hostId, this.guestId, this.whoseTurn(), this.board.getFields(), this.gameStatus);
    }

    public void move(String clientId, int x, int y) throws GameRuleException {
        if (!this.gameStarted()) {
            throw InvalidMoveException.becauseGameHasntStarted();
        }

        Board toCalculate = this.board.copy();
        toCalculate.makeMove(clientId, x, y);
        this.gameRule.apply(clientId, x, y, this.board, toCalculate, this);
        this.board = toCalculate;

        this.setUserSkippedTurn(clientId, false);
        if (!clientId.equals("BOT")) {
            this.recordThat(new ClientMovedEvent(this.id, x, y, this.getGameModel(clientId)));
        }
        this.changeTurn();
    }

    public void addPoints(String clientId, int amount) {
        if (this.hostId.equals(clientId)) {
            this.hostPoints += amount;
        } else {
            this.guestPoints += amount;
        }
    }

    public void start() {
        this.turn = this.hostId;
        this.gameStatus = GameStatus.STARTED;
    }

    public void join(String clientId) throws CannotJoinRoomException {
        if (this.isRoomFull()) {
            throw CannotJoinRoomException.becauseRoomIsFull();
        }

        this.guestId = clientId;
        this.recordThat(new ClientJoinedGameEvent(this.hostId, clientId, this.id));
    }

    public String getId() {
        return this.id;
    }

    public int getPlayersCount()
    {
        return this.isRoomFull() ? 2 : 1;
    }

    public String whoseTurn() {
        return this.turn;
    }

    public void finishGame(boolean surrender)
    {
        this.winnerId = guestId;
        if (!surrender) {

        }
        this.gameStatus = GameStatus.FINISHED;
        this.recordThat(new GameFinishedEvent(this.getGameModel(this.winnerId), this.winnerId, this.hostPoints, this.guestPoints, surrender));
    }

    public void surrender(String clientId)
    {
        this.winnerId = this.hostId.equals(clientId) ? this.guestId : this.hostId;
        this.finishGame(true);
    }

    public void skipTurn(String clientId) throws InvalidMoveException {
        if (!this.turn.equals(clientId)) {
            throw InvalidMoveException.becauseNotPlayersTurn();
        }
        this.setUserSkippedTurn(clientId, true);
        this.checkGameFinish();

        this.changeTurn();
        this.recordThat(new ClientSkippedTurnEvent(this.getGameModel(clientId)));
    }

    private void checkGameFinish()
    {
        if (this.guestSkippedLastTurn && this.hostSkippedLastTurn) {
            this.finishGame(false);
        }
    }

    private void setUserSkippedTurn(String clientId, boolean skipped)
    {
        if (this.hostId.equals(clientId)) {
            this.hostSkippedLastTurn = skipped;
        } else {
            this.guestSkippedLastTurn = skipped;
        }
    }

    private boolean gameStarted() {
        return this.gameStatus.equals(GameStatus.STARTED);
    }

    private boolean isRoomFull() {
        return this.hostId != null && this.guestId != null;
    }

    private static boolean canClientCreateGame(String clientId, GameRegistry gameRegistry)
    {
        try {
            gameRegistry.getByClientId(clientId);
        } catch (GameDoesntExistsException e) {
            return true;
        }
        return false;
    }

    private void changeTurn() {
        if (this.turn.equals(this.hostId)) {
            this.turn = this.guestId;
        } else {
            this.turn = this.hostId;
        }
    }
}
