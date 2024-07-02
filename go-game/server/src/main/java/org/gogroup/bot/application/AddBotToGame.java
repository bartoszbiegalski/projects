package org.gogroup.bot.application;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.GameDoesntExistsException;

public class AddBotToGame {
    private GameRegistry gameRegistry;

    public AddBotToGame(GameRegistry gameRegistry) {
        this.gameRegistry = gameRegistry;
    }

    public void execute(String clientId) throws GameDoesntExistsException, CannotJoinRoomException {
        Game game = this.gameRegistry.getByClientId(clientId);
        game.join("BOT");
        game.start();
    }
}
