package org.gogroup.game.domain.exception;

public class CannotCreateNewGameException extends GameDomainException
{
    public CannotCreateNewGameException(String message)
    {
        super(message);
    }

    public static CannotCreateNewGameException becauseUserHasRunningGame() {
        return new CannotCreateNewGameException("You can't create new game, because you have still running another game.");
    }
}
