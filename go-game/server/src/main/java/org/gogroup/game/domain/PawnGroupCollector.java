package org.gogroup.game.domain;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PawnGroupCollector {
    public ArrayList<PawnGroup> collect(Board board)
    {
        ArrayList<PawnGroup> groupedPawns = new ArrayList<>();
        String[][] fields = board.getFields();
        int boardSize = fields.length;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (fields[i][j] == null) {
                    continue;
                }

                Pawn pawn = new Pawn(j, i, fields[i][j]);
                PawnGroup group = this.getGroupWhichContainsThisPawn(groupedPawns, pawn);

                this.addLeftNeighborToGroup(group, board, pawn);
                this.addRightNeighborToGroup(group, board, pawn);
                this.addTopNeighborToGroup(group, board, pawn);
                this.addBottomNeighborToGroup(group, board, pawn);
            }
        }

        return groupedPawns;
    }

    private void addLeftNeighborToGroup(PawnGroup group, Board board, Pawn pawn)
    {
        Pawn neighbor = pawn.getLeftNeighbor(board);
        if (this.shouldAddNeighbor(pawn, neighbor, group)) {
            group.add(neighbor);
        }
    }

    private void addRightNeighborToGroup(PawnGroup group, Board board, Pawn pawn)
    {
        Pawn neighbor = pawn.getRightNeighbor(board);
        if (this.shouldAddNeighbor(pawn, neighbor, group)) {
            group.add(neighbor);
        }
    }

    private void addTopNeighborToGroup(PawnGroup group, Board board, Pawn pawn)
    {
        Pawn neighbor = pawn.getTopNeighbor(board);
        if (this.shouldAddNeighbor(pawn, neighbor, group)) {
            group.add(neighbor);
        }
    }

    private void addBottomNeighborToGroup(PawnGroup group, Board board, Pawn pawn)
    {
        Pawn neighbor = pawn.getBottomNeighbor(board);
        if (this.shouldAddNeighbor(pawn, neighbor, group)) {
            group.add(neighbor);
        }
    }

    private boolean shouldAddNeighbor(Pawn pawn, Pawn neighbor, PawnGroup pawnGroup)
    {
        return neighbor != null &&
               neighbor.getOwnerId() != null &&
               neighbor.getOwnerId().equals(pawn.getOwnerId()) &&
               pawnGroup.getPawn(neighbor.getX(), neighbor.getY()) == null;
    }

    private PawnGroup getGroupWhichContainsThisPawn(ArrayList<PawnGroup> groupedPawns, Pawn pawn)
    {
        AtomicReference<PawnGroup> pawnGroup = new AtomicReference<>();
        groupedPawns.forEach(group -> {
            Pawn foundPawn = group.getPawn(pawn.getX(), pawn.getY());
            if (foundPawn == null) {
                return;
            }
            pawnGroup.set(group);
        });

        if (pawnGroup.get() != null) {
            return pawnGroup.get();
        }

        PawnGroup newGroup = new PawnGroup();
        newGroup.add(pawn);
        groupedPawns.add(newGroup);
        return newGroup;
    }
}
