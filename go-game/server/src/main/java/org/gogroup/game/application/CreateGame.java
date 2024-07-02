package org.gogroup.game.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.gameRule.GameRule;

public class CreateGame {
    private final GameRegistry gameRegistry;

    private final GameRule gameRule;

    public CreateGame(GameRegistry gameRegistry, GameRule gameRule)
    {
        this.gameRegistry = gameRegistry;
        this.gameRule = gameRule;
    }

    public void execute(int size, String clientId) throws CannotCreateNewGameException {
        Game.createFromClientId(size, clientId, this.gameRegistry, this.gameRule);
    }
}
