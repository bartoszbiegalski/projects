package org.gogroup.game.domain.exception;

public class GameHasntStartedException extends GameDomainException
{
    public GameHasntStartedException(String message) {
        super(message);
    }

    public static GameHasntStartedException create()
    {
        String message = "Game has not started yet";
        return new GameHasntStartedException(message);
    }
}
