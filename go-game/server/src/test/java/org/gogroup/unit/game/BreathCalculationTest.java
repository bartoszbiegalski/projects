package org.gogroup.unit.game;

import junit.framework.TestCase;
import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.Pawn;
import org.gogroup.game.domain.PawnGroup;

public class BreathCalculationTest extends TestCase
{
    public void testSystemCalculatesProperlyBreathsOfGroupInCorner()
    {
        // GIVEN
        Board board = new Board(19);
        board.makeMove("test", 0, 0);
        board.makeMove("test", 1, 0);
        board.makeMove("test", 0, 1);

        PawnGroup pawnGroup = new PawnGroup();
        pawnGroup.add(new Pawn(0, 0, "test"));
        pawnGroup.add(new Pawn(1, 0, "test"));
        pawnGroup.add(new Pawn(0, 1, "test"));

        // WHEN
        pawnGroup.calculateBreaths(board);

        // THEN
        assertSame(3, pawnGroup.getBreaths());
    }

    public void testSystemCalculatesProperlyBreathsOfDeathGroup()
    {
        // GIVEN
        Board board = new Board(19);
        board.makeMove("test", 10, 10);
        board.makeMove("test", 10, 11);
        board.makeMove("test", 11, 10);
        board.makeMove("test", 11, 11);

        board.makeMove("enemy", 9, 10);
        board.makeMove("enemy", 9, 11);
        board.makeMove("enemy", 12, 10);
        board.makeMove("enemy", 12, 11);
        board.makeMove("enemy", 10, 9);
        board.makeMove("enemy", 11, 9);
        board.makeMove("enemy", 10, 12);
        board.makeMove("enemy", 11, 12);

        PawnGroup pawnGroup = new PawnGroup();
        pawnGroup.add(new Pawn(10, 10, "test"));
        pawnGroup.add(new Pawn(10, 11, "test"));
        pawnGroup.add(new Pawn(11, 10, "test"));
        pawnGroup.add(new Pawn(11, 11, "test"));

        // WHEN
        pawnGroup.calculateBreaths(board);

        // THEN
        assertSame(0, pawnGroup.getBreaths());
    }
}
