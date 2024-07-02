package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Game;
import org.gogroup.game.domain.GameRegistry;
import org.gogroup.game.domain.exception.CannotCreateNewGameException;
import org.gogroup.game.domain.exception.CannotJoinRoomException;
import org.gogroup.game.domain.exception.GameRuleException;
import org.gogroup.shared.readModel.BoardModel;
import org.gogroup.game.infrastructure.inMemory.InMemoryGameRegistry;
import org.gogroup.unit.game.testDoubles.DummyGameRule;

public class MoveTest extends TestCase
{
    public void testSystemBlocksMovingIfGameHasntStarted() throws CannotCreateNewGameException, CannotJoinRoomException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // GIVEN
        Game game = Game.createFromClientId(19, "test-client", gameRegistry, new DummyGameRule(null));
        game.join("another-client");

        // WHEN
        Throwable exception = null;
        try {
            game.move("test-client", 5, 5);
        } catch (GameRuleException invalidMoveException) {
            exception = invalidMoveException;
        }
        assertNotNull(exception);
    }

    public void testSystemAllowsMovingToEmptyFields() throws CannotCreateNewGameException, CannotJoinRoomException, GameRuleException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // GIVEN
        Game game = Game.createFromClientId(19, "test-client", gameRegistry, new DummyGameRule(null));
        game.join("another-client");
        game.start();

        // WHEN
        game.move("test-client", 5, 5);

        // THEN
        BoardModel boardModel = game.getGameModel("test-client").getBoard();
        assertNotNull(boardModel.getBoard()[5][5]);
    }

    public void testSystemBlocksMovingToBusyFields() throws CannotCreateNewGameException, CannotJoinRoomException, GameRuleException {
        // PREPARE
        GameRegistry gameRegistry = new InMemoryGameRegistry();

        // GIVEN
        Game game = Game.createFromClientId(19, "test-client", gameRegistry, new DummyGameRule(null));
        game.join("another-client");
        game.start();

        // WHEN
        game.move("test-client", 5, 5);

        // THEN
        BoardModel boardModel = game.getGameModel("test-client").getBoard();
        assertNotNull(boardModel.getBoard()[5][5]);
    }
}
