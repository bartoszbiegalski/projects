package org.gogroup.unit.game.testDoubles;

import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.game.domain.gameRule.GameRule;

public class DummyGameRule extends GameRule
{
    public DummyGameRule(GameRule next) {
        super(next);
    }

    @Override
    public void apply(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException {

    }
}
