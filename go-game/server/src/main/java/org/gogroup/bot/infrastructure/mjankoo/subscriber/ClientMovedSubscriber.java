package org.gogroup.bot.infrastructure.mjankoo.subscriber;

import org.gogroup.bot.domain.Bot;
import org.gogroup.bot.domain.Move;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.event.ClientMovedEvent;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.game.domain.exception.GameRuleException;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientMovedSubscriber implements EventSubscriberInterface {
    private Bot bot;

    private GameRegistry gameRegistry;

    public ClientMovedSubscriber(Bot bot, GameRegistry gameRegistry) {
        this.bot = bot;
        this.gameRegistry = gameRegistry;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(ClientMovedEvent.class, new ArrayList<>(
                    Arrays.asList("makeBotMove", 0)
            ));
        }};
    }

    public void makeBotMove(ClientMovedEvent event) throws GameDoesntExistsException {
        if (event.getClientId().equals("BOT") || !event.getGameModel().getGuestId().equals("BOT")) {
            return;
        }

        Game game = this.gameRegistry.get(event.getGameId());
        this.makeMove(game);
    }

    private void makeMove(Game game) {
        try {
            Move move = this.bot.makeMove(game.getGameModel("BOT").getBoard());
            game.move("BOT", move.getX(), move.getY());
        } catch (GameRuleException e) {
            this.makeMove(game);
        }
    }
}
