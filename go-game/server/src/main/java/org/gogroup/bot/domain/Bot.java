package org.gogroup.bot.domain;

import org.gogroup.shared.readModel.BoardModel;

public class Bot {
    public Move makeMove(BoardModel board) {
        Integer x = this.getRandomNumber(0, board.getBoard().length);
        Integer y = this.getRandomNumber(0, board.getBoard().length);

        return new Move(x, y);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
