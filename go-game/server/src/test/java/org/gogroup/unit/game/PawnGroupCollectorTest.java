package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.PawnGroup;
import org.gogroup.game.domain.PawnGroupCollector;

import java.util.ArrayList;

public class PawnGroupCollectorTest extends TestCase {
    public void testSystemCollectsGroups() {
        Board board = new Board(19);
        board.makeMove("test1", 0, 0);
        board.makeMove("test1", 1, 0);
        board.makeMove("test1", 2, 0);

        board.makeMove("test2", 4, 0);
        board.makeMove("test1", 2, 10);
        board.makeMove("test1", 2, 11);

        PawnGroupCollector pawnGroupCollector = new PawnGroupCollector();
        ArrayList<PawnGroup> pawnGroups = pawnGroupCollector.collect(board);

        assertSame(3, pawnGroups.size());
    }
}
