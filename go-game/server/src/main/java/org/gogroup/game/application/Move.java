package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;

public class Move {
    private GameRegistry gameRegistry;

    private EventDispatcherInterface eventDispatcher;

    public Move(GameRegistry gameRegistry, EventDispatcherInterface eventDispatcher) {
        this.gameRegistry = gameRegistry;
        this.eventDispatcher = eventDispatcher;
    }

    public GameModel execute(String clientId, int x, int y) throws GameDoesntExistsException, GameRuleException {
        Game game = this.gameRegistry.getByClientId(clientId);
        game.move(clientId, x, y);
        game.dispatchDomainEvents(this.eventDispatcher);
        return game.getGameModel(clientId);
    }
}
