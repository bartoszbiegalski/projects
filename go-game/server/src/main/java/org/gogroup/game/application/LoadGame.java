package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.shared.readModel.GameModel;

public class LoadGame
{
    private final GameRegistry gameRegistry;

    public LoadGame(GameRegistry gameRegistry) {
        this.gameRegistry = gameRegistry;
    }

    public GameModel execute(String clientId) throws GameDoesntExistsException {
        Game game = this.gameRegistry.getByClientId(clientId);
        return game.getGameModel(clientId);
    }
}
