package org.gogroup.game.domain.event;

import org.gogroup.shared.AbstractDomainEvent;
import org.gogroup.shared.readModel.GameModel;

public class GameFinishedEvent extends AbstractDomainEvent
{
    private GameModel gameModel;
    private String winnerId;

    private Integer hostPoints;

    private Integer guestPoints;

    private boolean surrender;

    public GameFinishedEvent(GameModel gameModel, String winnerId, Integer hostPoints, Integer guestPoints, boolean surrender)
    {
        this.gameModel = gameModel;
        this.winnerId = winnerId;
        this.hostPoints = hostPoints;
        this.guestPoints = guestPoints;
        this.surrender = false;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public Integer getHostPoints() {
        return hostPoints;
    }

    public Integer getGuestPoints() {
        return guestPoints;
    }

    public boolean isSurrender() {
        return surrender;
    }
}
