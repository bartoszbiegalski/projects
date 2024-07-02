package org.gogroup.game.domain.gameRule;

import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.GameRuleException;

public abstract class GameRule
{
    protected GameRule next;

    public GameRule(GameRule next)
    {
        this.next = next;
    }

    public abstract void apply(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException;

    protected void executeNext(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException {
        if (this.next != null) {
            this.next.apply(clientId, x, y, original, toCalculate, game);
        }
    };
}
