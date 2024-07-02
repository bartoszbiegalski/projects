package org.gogroup.game.domain;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class PawnGroup {
    private final ArrayList<Pawn> pawns = new ArrayList<>();

    private int breaths = 0;

    private String ownerId;

    public void add(Pawn pawn) {
        if (this.ownerId == null) {
            this.ownerId = pawn.getOwnerId();
        }

        if (!pawn.getOwnerId().equals(this.ownerId)) {
            throw new RuntimeException("Can't add another player pawn to the group.");
        }
        this.pawns.add(pawn);
    }

    public ArrayList<Pawn> getPawns() {
        return this.pawns;
    }

    public Pawn getPawn(int x, int y)
    {
        Stream<Pawn> filtered = this.pawns.stream().filter(pawn -> pawn.getX() == x && pawn.getY() == y);
        return filtered.findFirst().orElse(null);
    }

    public int getBreaths() {
        return this.breaths;
    }

    public void calculateBreaths(Board board)
    {
        ArrayList<String> calculatedBreaths = new ArrayList<>();
        AtomicInteger breaths = new AtomicInteger();

        this.pawns.forEach(pawn -> {
            Pawn leftNeighbor = pawn.getLeftNeighbor(board);
            Pawn rightNeighbor = pawn.getRightNeighbor(board);
            Pawn topNeighbor = pawn.getTopNeighbor(board);
            Pawn bottomNeighbor = pawn.getBottomNeighbor(board);

            if (this.shouldCalculateBreath(leftNeighbor, calculatedBreaths)) {
                calculatedBreaths.add(leftNeighbor.getKey());
                breaths.getAndIncrement();
            }
            if (this.shouldCalculateBreath(rightNeighbor, calculatedBreaths)) {
                calculatedBreaths.add(rightNeighbor.getKey());
                breaths.getAndIncrement();
            }
            if (this.shouldCalculateBreath(topNeighbor, calculatedBreaths)) {
                calculatedBreaths.add(topNeighbor.getKey());
                breaths.getAndIncrement();
            }
            if (this.shouldCalculateBreath(bottomNeighbor, calculatedBreaths)) {
                calculatedBreaths.add(bottomNeighbor.getKey());
                breaths.getAndIncrement();
            }
        });

        this.breaths = breaths.get();
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    private boolean shouldCalculateBreath(Pawn neighbour, ArrayList<String> calculatedBreaths)
    {
        return neighbour != null &&
                neighbour.getOwnerId() == null &&
                !calculatedBreaths.contains(neighbour.getKey());
    }
}
