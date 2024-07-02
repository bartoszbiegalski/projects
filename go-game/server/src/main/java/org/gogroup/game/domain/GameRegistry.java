package org.gogroup.game.domain;

import org.gogroup.game.domain.exception.GameDoesntExistsException;

import java.util.HashMap;

public interface GameRegistry {
    void add(Game game);

    Game getByClientId(String clientId) throws GameDoesntExistsException;

    Game get(String id) throws GameDoesntExistsException;

    HashMap<String, Game> getGames();
}
