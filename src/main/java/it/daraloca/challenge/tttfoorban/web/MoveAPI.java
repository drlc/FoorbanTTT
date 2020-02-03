package it.daraloca.challenge.tttfoorban.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daraloca.challenge.tttfoorban.dto.MoveDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveResultDTO;
import it.daraloca.challenge.tttfoorban.service.MoveSrv;

/**
 * MoveAPI
 */
@RestController
@RequestMapping("/game/{gameId}/move/")
public class MoveAPI {

    @Autowired
    private MoveSrv moveSrv;

    /**
     * This method returns the moves of a specified game
     * 
     * @param gameId the identifier of the game
     * @return the list of the moves of the requested game
     */
    @GetMapping()
    public List<MoveDTO> getList(@PathVariable("gameId") UUID gameId) {
        return moveSrv.findAll(gameId);
    }

    /**
     * This method creates a move for the specified game.
     * 
     * @param gameId the identifier of the game
     * @param move   the move to save
     * @return the winner if game has ended; the result of the move if still on
     *         game; an error otherwise
     */
    @PostMapping()
    public MoveResultDTO move(@PathVariable("gameId") UUID gameId, @RequestBody MoveDTO move) {
        return moveSrv.move(gameId, move);
    }

}