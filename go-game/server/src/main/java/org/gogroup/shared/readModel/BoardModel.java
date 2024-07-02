package org.gogroup.shared.readModel;

public class BoardModel
{
    private final String[][] board;

    public BoardModel(String hostId, String[][] board)
    {
        this.board = new String[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    this.board[i][j] = null;
                } else if (hostId.equals(board[i][j])) {
                    this.board[i][j] = "H";
                } else {
                    this.board[i][j] = "G";
                }
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }
}
