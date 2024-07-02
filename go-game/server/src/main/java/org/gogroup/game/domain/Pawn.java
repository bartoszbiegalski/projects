package org.gogroup.game.domain;

public class Pawn {
    private final int x;

    private final int y;

    private final String ownerId;

    public Pawn(int x, int y, String ownerId)
    {
        this.x = x;
        this.y = y;
        this.ownerId = ownerId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getKey() {
        return this.x + "," + this.y;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public Pawn getLeftNeighbor(Board board)
    {
        if (this.getX() == 0) {
            return null;
        }

        int x = this.getX() - 1;
        int y = this.getY();
        return new Pawn(x, y, board.getFields()[y][x]);
    }

    public Pawn getRightNeighbor(Board board)
    {
        if (this.getX() == board.getFields().length - 1) {
            return null;
        }

        int x = this.getX() + 1;
        int y = this.getY();
        return new Pawn(x, y, board.getFields()[y][x]);
    }

    public Pawn getTopNeighbor(Board board)
    {
        if (this.getY() == 0) {
            return null;
        }

        int x = this.getX();
        int y = this.getY() - 1;
        return new Pawn(x, y, board.getFields()[y][x]);
    }

    public Pawn getBottomNeighbor(Board board)
    {
        if (this.getY() == board.getFields().length - 1) {
            return null;
        }

        int x = this.getX();
        int y = this.getY() + 1;
        return new Pawn(x, y, board.getFields()[y][x]);
    }
}
