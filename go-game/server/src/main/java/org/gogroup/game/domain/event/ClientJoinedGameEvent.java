package org.gogroup.game.domain.event;

import org.gogroup.shared.AbstractDomainEvent;

public class ClientJoinedGameEvent extends AbstractDomainEvent
{
    private final String hostId;

    private final String clientId;

    private final String gameId;

    public ClientJoinedGameEvent(String hostId, String clientId, String gameId) {
        this.hostId = hostId;
        this.clientId = clientId;
        this.gameId = gameId;
    }

    public String getHostId() {
        return this.hostId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getGameId() {
        return this.gameId;
    }
}
