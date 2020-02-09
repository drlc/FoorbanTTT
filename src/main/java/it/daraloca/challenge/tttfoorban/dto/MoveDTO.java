package it.daraloca.challenge.tttfoorban.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import it.daraloca.challenge.tttfoorban.enums.GamerEnum;
import lombok.Data;

/**
 * MoveDTO
 */
@Data
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

    public MoveDTO() {
    }

    public MoveDTO(Integer x, Integer y, GamerEnum value, Date date) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.date = date;
    }

}