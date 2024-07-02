package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.InvalidMoveException;
import org.gogroup.game.domain.gameRule.EmptyRule;
import org.gogroup.game.infrastructure.inMemory.InMemoryGameRegistry;

public class SkipTurnTest extends TestCase {
    public void testClientCannotSkipNotHisTurn() throws CannotCreateNewGameException, CannotJoinRoomException {
        // GIVEN
        Game game = Game.createFromClientId(10, "test-host", new InMemoryGameRegistry(), new EmptyRule());
        game.join("test-guest");
        game.start();

        // WHEN
        Throwable exception = null;
        try {
            game.skipTurn("test-guest");
        } catch (InvalidMoveException invalidMoveException) {
            exception = invalidMoveException;
        }
        assertNotNull(exception);
    }
}
