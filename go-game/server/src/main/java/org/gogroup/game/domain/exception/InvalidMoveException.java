package org.gogroup.game.domain.exception;

public class InvalidMoveException extends GameRuleException
{
    public InvalidMoveException(String message) {
        super(message);
    }

    public static InvalidMoveException becauseNotPlayersTurn() {
        String message = "It's not your turn.";
        return new InvalidMoveException(message);
    }

    public static InvalidMoveException becauseGameHasntStarted() {
        String message = "Game has not started yet.";
        return new InvalidMoveException(message);
    }

    public static InvalidMoveException becauseFieldIsBusy(int x, int y) {
        String message = "Selected field is busy.";
        return new InvalidMoveException(message);
    }

    public static InvalidMoveException becauseNoBreaths(int x, int y) {
        String message = "This field has no breaths";
        return new InvalidMoveException(message);
    }
}
