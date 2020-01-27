package it.daraloca.challenge.tttfoorban.data.move;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import it.daraloca.challenge.tttfoorban.data.game.Game;
import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * Move
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Move implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -23573485743877534L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    /**
     * Coordinate X
     */
    private Integer x;
    /**
     * Coordinate Y
     */
    private Integer y;
    /**
     * Value of the cell
     */
    private GamerEnum value;
    /**
     * WHen the move has been done
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Game game;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public GamerEnum getValue() {
        return this.value;
    }

    public void setValue(GamerEnum value) {
        this.value = value;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}