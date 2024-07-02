package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.GameDoesntExistsException;
import org.gogroup.game.infrastructure.inMemory.InMemoryGameRegistry;
import org.gogroup.unit.game.testDoubles.DummyGameRule;

public class CreateGameTest extends TestCase
{
    public void testSystemCreatesNewGame() throws CannotCreateNewGameException, GameDoesntExistsException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // WHEN
        Game.createFromClientId(19, "test-client", gameRegistry, new DummyGameRule(null));

        // THEN
        Game game = gameRegistry.getByClientId("test-client");
        assertNotNull(game);
    }

    public void testSystemBlocksCreatingNewGameIfClientIsInAnotherRoom() throws CannotCreateNewGameException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // GIVEN
        Game.createFromClientId(19, "test-client", gameRegistry,  new DummyGameRule(null));

        // WHEN
        Throwable exception = null;
        try {
            Game.createFromClientId(19, "test-client", gameRegistry,  new DummyGameRule(null));
        } catch (CannotCreateNewGameException cannotCreateNewGameException) {
            exception = cannotCreateNewGameException;
        }

        // THEN
        assertNotNull(exception);
    }
}
