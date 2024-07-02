package org.gogroup.game.domain.gameRule;

import org.gogroup.game.domain.*;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.game.domain.exception.InvalidMoveException;

import java.util.ArrayList;

public class BreathsRule extends GameRule
{
    private PawnGroupCollector pawnGroupCollector;

    public BreathsRule(GameRule next, PawnGroupCollector pawnGroupCollector) {
        super(next);
        this.pawnGroupCollector = pawnGroupCollector;
    }

    @Override
    public void apply(String clientId, int x, int y, Board original, Board toCalculate, Game game) throws GameRuleException
    {
        ArrayList<PawnGroup> groups = this.pawnGroupCollector.collect(toCalculate);
        for (PawnGroup group : groups) {
            group.calculateBreaths(toCalculate);
            if (group.getBreaths() > 0) {
                continue;
            }

            if (clientId.equals(group.getOwnerId())) {
                throw InvalidMoveException.becauseNoBreaths(x, y);
            } else {
                group.getPawns().forEach(pawn -> {
                    toCalculate.remove(pawn.getX(), pawn.getY());
                });
                game.addPoints(clientId, group.getPawns().size());
            }
        }

        this.executeNext(clientId, x, y, original, toCalculate, game);
    }
}
