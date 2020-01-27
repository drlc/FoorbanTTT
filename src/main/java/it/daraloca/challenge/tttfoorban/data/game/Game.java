package it.daraloca.challenge.tttfoorban.data.game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import it.daraloca.challenge.tttfoorban.data.move.Move;
import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * Game
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Game implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2357348574387534L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    /**
     * The dimension of the grid
     */
    private Integer dimension;
    /**
     * The number of player
     */
    private Integer numPlayer;
    /**
     * The winner of this math
     */
    private GamerEnum winner;

    /**
     * The game from which this game is originated from (useful to get who will
     * start this game)
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    private Game fatherGame;

    /**
     * The moves related to this game
     */
    @OneToMany(mappedBy = "game")
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

    public GamerEnum getWinner() {
        return this.winner;
    }

    public void setWinner(GamerEnum winner) {
        this.winner = winner;
    };

    public Game getFatherGame() {
        return this.fatherGame;
    }

    public void setFatherGame(Game fatherGame) {
        this.fatherGame = fatherGame;
    };

    public Set<Move> getMoves() {
        return this.moves;
    }

}