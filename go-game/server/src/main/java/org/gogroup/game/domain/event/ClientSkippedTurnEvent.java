package org.gogroup.game.domain.event;

import org.gogroup.shared.readModel.GameModel;
import org.gogroup.shared.AbstractDomainEvent;

public class ClientSkippedTurnEvent extends AbstractDomainEvent
{
    private final GameModel gameModel;

    public ClientSkippedTurnEvent(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
