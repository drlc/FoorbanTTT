package it.daraloca.challenge.tttfoorban.web;

import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daraloca.challenge.tttfoorban.data.game.Game;
import it.daraloca.challenge.tttfoorban.dto.GameDTO;
import it.daraloca.challenge.tttfoorban.service.GameSrv;

/**
 * GameAPI
 */
@RestController
@RequestMapping("/game/")
public class GameAPI {

    @Autowired
    private GameSrv gameSrv;

    /**
     * THis API returns a list of paginated GameDTO corrisponding the filter and the
     * page requested.
     * 
     * @param predicate filter based on Game.class properties
     * @param pageable  the pageable to extract
     * @return the page corrisponding on passed request
     */
    @GetMapping()
    public Page<GameDTO> getList(@QuerydslPredicate(root = Game.class) Predicate predicate,
            @PageableDefault(page = 0, size = 100, direction = Direction.ASC) Pageable pageable) {
        return gameSrv.findAll(predicate, pageable);
    }

    /**
     * This API returns the information of the specified Game.
     * 
     * @param gameId the identifier of the game
     * @return the information of the requested game
     */
    @GetMapping(value = "{gameId}/")
    public GameDTO getList(@PathVariable("gameId") UUID gameId) {
        return gameSrv.findOne(gameId);
    }

    /**
     * This API creates a new Game.
     * 
     * @param game the information of the game to create
     * @return the identifier of the new created game
     */
    @PostMapping()
    public UUID create(@RequestBody GameDTO game) {
        return gameSrv.create(game);
    }

}