package org.gogroup.game.domain.event;

import org.gogroup.shared.readModel.GameModel;
import org.gogroup.shared.AbstractDomainEvent;

public class ClientMovedEvent extends AbstractDomainEvent
{
    private String gameId;

    private int x;
    private int y;
    private GameModel gameModel;

    public ClientMovedEvent(String gameId, int x, int y, GameModel gameModel) {
        this.gameId = gameId;
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
    }

    public String getGameId() {
        return this.gameId;
    }

    public String getClientId() {
        return this.gameModel.getClientId();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameModel getGameModel() {
        return this.gameModel;
    }
}
