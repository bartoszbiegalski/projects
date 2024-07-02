package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.game.domain.exception.InvalidMoveException;
import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;

public class SkipTurn
{
    private final GameRegistry gameRegistry;

    private final EventDispatcherInterface eventDispatcher;

    public SkipTurn(GameRegistry gameRegistry, EventDispatcherInterface eventDispatcher) {
        this.gameRegistry = gameRegistry;
        this.eventDispatcher = eventDispatcher;
    }

    public void execute(String clientId) throws GameDoesntExistsException, InvalidMoveException
    {
        Game game = this.gameRegistry.getByClientId(clientId);
        game.skipTurn(clientId);
        game.dispatchDomainEvents(eventDispatcher);
    }
}
