package org.gogroup.gameRetrospective.domain;


import jakarta.persistence.*;

@Entity
@Table(name="Move")
public class Move {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String gameId;

    @Column
    private String clientId;

    @Column
    private int x;

    @Column
    private int y;

    @Column
    private int movePosition;

    @Column
    private int boardSize;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMovePosition() {
        return movePosition;
    }

    public void setMovePosition(int movePosition) {
        this.movePosition = movePosition;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
