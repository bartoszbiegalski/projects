package org.gogroup.game.domain.gameRule;

import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.GameRuleException;

public class EmptyRule extends GameRule {
    public EmptyRule() {
        super(null);
    }

    @Override
    public void apply(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException {

    }
}
