package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;

import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * MoveResultDTO
 */
public class MoveResultDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7880692290116886375L;

    private GamerEnum nextToMove;
    private Boolean isWinner;

    public GamerEnum getNextToMove() {
        return this.nextToMove;
    }

    public void setNextToMove(GamerEnum nextToMove) {
        this.nextToMove = nextToMove;
    }

    public Boolean getIsWinner() {
        return this.isWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }

}