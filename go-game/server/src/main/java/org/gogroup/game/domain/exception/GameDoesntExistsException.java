package org.gogroup.game.domain.exception;

public class GameDoesntExistsException extends GameDomainException
{
    public GameDoesntExistsException(String message) {
        super(message);
    }

    public static GameDoesntExistsException fromId(String id)
    {
        String message = "Game does not exists.";
        return new GameDoesntExistsException(message);
    }

    public static GameDoesntExistsException fromClientId(String id)
    {
        String message = "Game for this client does not exists.";
        return new GameDoesntExistsException(message);
    }
}
