package org.gogroup.bot.domain;

public class Move {
    private Integer x;

    private Integer y;

    public Move(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }
}
