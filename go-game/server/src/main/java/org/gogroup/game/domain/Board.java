package org.gogroup.game.domain;

import org.gogroup.shared.AbstractAggregate;

public class Board extends AbstractAggregate
{
    private String[][] board;

    public Board(int size)
    {
        this.board = new String[size][size];
    }

    public String[][] getFields() {
        return this.board;
    }

    public boolean isPositionFree(int x, int y)
    {
        return this.board[y][x] == null;
    }

    public void makeMove(String clientId, int x, int y)
    {
        this.board[y][x] = clientId;
    }

    public void remove(int x, int y) {
        this.board[y][x] = null;
    }

    public Board copy() {
        int size = this.board.length;
        String[][] boardFields = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardFields[i][j] = board[i][j];
            }
        }
        Board board = new Board(this.board.length);
        board.board = boardFields;
        return board;
    }
}
