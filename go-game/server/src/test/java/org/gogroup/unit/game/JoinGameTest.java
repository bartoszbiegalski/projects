package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.infrastructure.inMemory.InMemoryGameRegistry;
import org.gogroup.unit.game.testDoubles.DummyGameRule;

public class JoinGameTest extends TestCase {
    public void testSystemBlocksJoiningToFullRoom() throws CannotCreateNewGameException, CannotJoinRoomException {
        Game game = Game.createFromClientId(13, "test1", new InMemoryGameRegistry(), new DummyGameRule(null));

        // GIVEN
        game.join("test2");

        // WHEN
        Throwable exception = new Exception();
        try {
            game.join("");
        } catch (CannotJoinRoomException cannotJoinRoomException) {
            exception = cannotJoinRoomException;
        }

        assertTrue(exception instanceof CannotJoinRoomException);
        assertSame(exception.getMessage(), "You can't join room because is is full.");
    }
}
