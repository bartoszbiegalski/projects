package org.gogroup.gameRetrospective.domain.exception;

public class NoMoreMovesException extends GameRetrospectiveDomainException {
    public NoMoreMovesException(String message) {
        super(message);
    }

    public static NoMoreMovesException create() {
        return new NoMoreMovesException("No more moves.");
    }
}
