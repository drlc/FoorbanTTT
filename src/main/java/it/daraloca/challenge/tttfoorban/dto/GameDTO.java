package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import it.daraloca.challenge.tttfoorban.enums.GamerEnum;
import lombok.Data;

/**
 * GameDTO
 */
@Data
public class GameDTO implements Serializable {

    // TODO missing validations

    /**
     *
     */
    private static final long serialVersionUID = 7597863502376462112L;

    private UUID id;

    private Integer dimension;
    private Integer numPlayer;

    private GamerEnum winner;

    private UUID fatherGameId;

    private final Set<MoveDTO> moves = new HashSet<>();

}