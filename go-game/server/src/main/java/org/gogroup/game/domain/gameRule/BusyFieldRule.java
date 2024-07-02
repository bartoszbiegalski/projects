package org.gogroup.game.domain.gameRule;

import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.game.domain.exception.InvalidMoveException;

public class BusyFieldRule extends GameRule
{
    public BusyFieldRule(GameRule next) {
        super(next);
    }

    @Override
    public void apply(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException {
        if (!original.isPositionFree(x, y)) {
            throw InvalidMoveException.becauseFieldIsBusy(x ,y);
        }
        this.executeNext(clientId, x, y, original, toCalculate, game);
    }
}
