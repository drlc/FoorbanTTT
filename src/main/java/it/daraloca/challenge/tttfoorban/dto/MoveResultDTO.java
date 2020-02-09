package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;

import it.daraloca.challenge.tttfoorban.enums.GamerEnum;
import lombok.Data;

/**
 * MoveResultDTO
 */
@Data
public class MoveResultDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7880692290116886375L;

    private GamerEnum nextToMove;
    private Boolean isWinner;

}