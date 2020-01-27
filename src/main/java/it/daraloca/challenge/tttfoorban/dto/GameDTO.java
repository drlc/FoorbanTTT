package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import it.daraloca.challenge.tttfoorban.data.move.Move;

/**
 * GameDTO
 */
public class GameDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7597863502376462112L;

    private UUID id;

    private Integer dimension;
    private Integer numPlayer;
    private Boolean ended;

    private UUID fatherGameId;

    private final Set<Move> moves = new HashSet<>();

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getDimension() {
        return this.dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Integer getNumPlayer() {
        return this.numPlayer;
    }

    public void setNumPlayer(Integer numPlayer) {
        this.numPlayer = numPlayer;
    }

    public Boolean getEnded() {
        return this.ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public UUID getFatherGameId() {
        return this.fatherGameId;
    }

    public void setFatherGameId(UUID fatherGameId) {
        this.fatherGameId = fatherGameId;
    };

    public Set<Move> getMoves() {
        return this.moves;
    }

}