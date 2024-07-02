package org.gogroup.game.domain.exception;

public class CannotJoinRoomException extends GameDomainException
{
    public CannotJoinRoomException(String message) {
        super(message);
    }

    public static CannotJoinRoomException becauseRoomIsFull() {
        String message = "You can't join room because is is full.";
        return new CannotJoinRoomException(message);
    }
}
