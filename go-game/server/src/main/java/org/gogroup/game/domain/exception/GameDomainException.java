package org.gogroup.game.domain.exception;

public abstract class GameDomainException extends Throwable
{
    public GameDomainException(String message)
    {
        super(message);
    }
}
