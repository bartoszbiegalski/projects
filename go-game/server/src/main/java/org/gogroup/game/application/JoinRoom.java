package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;

public class JoinRoom
{
    private final EventDispatcherInterface eventDispatcher;

    private final GameRegistry gameRegistry;

    public JoinRoom(EventDispatcherInterface eventDispatcher, GameRegistry gameRegistry)
    {
        this.eventDispatcher = eventDispatcher;
        this.gameRegistry = gameRegistry;
    }

    public void execute(String gameId, String clientId) throws GameDoesntExistsException, CannotJoinRoomException {
        Game game = this.gameRegistry.get(gameId);
        game.join(clientId);
        game.start();
        game.dispatchDomainEvents(eventDispatcher);
    }
}
