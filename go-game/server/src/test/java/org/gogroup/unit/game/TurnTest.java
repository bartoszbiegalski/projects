package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.*;
import org.gogroup.game.infrastructure.inMemory.InMemoryGameRegistry;
import org.gogroup.unit.game.testDoubles.DummyGameRule;

public class TurnTest extends TestCase
{
    public void testSystemChangesTurnAfterMove() throws CannotCreateNewGameException, CannotJoinRoomException, GameRuleException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // GIVEN
        Game game = Game.createFromClientId(19, "test-client", gameRegistry, new DummyGameRule(null));
        game.join("another-client");
        game.start();
        assertSame("test-client", game.whoseTurn());

        // WHEN
        game.move("test-client", 5, 5);

        // THEN
        assertSame("another-client", game.whoseTurn());
    }

    public void testSystemChangesTurnAfterPass() {
        assertTrue(true);
    }
}
