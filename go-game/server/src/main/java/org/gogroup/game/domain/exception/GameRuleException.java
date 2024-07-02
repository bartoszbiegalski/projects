package org.gogroup.game.domain.exception;

public abstract class GameRuleException extends GameDomainException {
    public GameRuleException(String message) {
        super(message);
    }
}
