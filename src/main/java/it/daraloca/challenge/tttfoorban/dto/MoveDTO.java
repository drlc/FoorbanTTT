package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * MoveDTO
 */
public class MoveDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4084616447863584212L;

    private UUID id;

    private Integer x;
    private Integer y;
    private GamerEnum value;
    private Date date;

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
        if (this.date != null) {
            return new Date(this.date.getTime());
        } else {
            return null;
        }

    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        } else {
            this.date = null;
        }

    }

}