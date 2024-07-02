package org.gogroup.game.infrastructure.inMemory;

import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.shared.readModel.GameModel;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryGameRegistry implements GameRegistry {
    private final HashMap<String, Game> games;

    public InMemoryGameRegistry()
    {
        this.games = new HashMap<>();
    }

    @Override
    public void add(Game game)
    {
        this.games.put(game.getId(), game);
    }

    @Override
    public Game getByClientId(String clientId) throws GameDoesntExistsException {
        AtomicReference<Game> clientGame = new AtomicReference<>();
        this.games.forEach((key, game) -> {
            GameModel gameModel = game.getGameModel(clientId);
            if (clientId.equals(gameModel.getHostId()) || clientId.equals(gameModel.getGuestId())) {
                clientGame.set(game);
            }
        });

        if(clientGame.get() == null) {
            throw GameDoesntExistsException.fromClientId(clientId);
        }
        return clientGame.get();
    }

    @Override
    public Game get(String id) throws GameDoesntExistsException
    {
        Game game = this.games.get(id);
        if(game == null) {
            throw GameDoesntExistsException.fromId(id);
        }
        return game;
    }

    @Override
    public HashMap<String, Game> getGames()
    {
        return this.games;
    }
}
