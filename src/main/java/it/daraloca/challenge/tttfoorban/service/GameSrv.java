package it.daraloca.challenge.tttfoorban.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.querydsl.core.types.Predicate;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.daraloca.challenge.tttfoorban.data.game.Game;
import it.daraloca.challenge.tttfoorban.data.game.GameRepository;
import it.daraloca.challenge.tttfoorban.dto.GameDTO;
import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * GameSrv
 */
@Service
public class GameSrv {

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private Mapper mapper;

	public Page<GameDTO> findAll(Predicate predicate, Pageable pageable) {
		Page<Game> pGame = gameRepo.findAll(predicate, pageable);
		List<GameDTO> list = pGame.getContent().stream().map(el -> {
			return mapper.map(el, GameDTO.class);
		}).collect(Collectors.toList());
		return new PageImpl<>(list, pageable, pGame.getTotalElements());
	}

	public GameDTO findOne(UUID gameId) {
		return mapper.map(gameRepo.findById(gameId), GameDTO.class);
	}

	public UUID create(GameDTO game) {
		if (game == null) {
			throw new RuntimeException("Passed Game is null");
		}
		return gameRepo.save(mapper.map(game, Game.class)).getId();
	}

	public void setWinner(UUID gameId, GamerEnum gamer){
		if(gameId == null || gamer == null){
			throw new RuntimeException("No gamer or GameID");
		}
		Game game = gameRepo.findById(gameId).orElseThrow(() -> new RuntimeException("No Game for the id"));
		game.setWinner(gamer);
		gameRepo.save(game);
	}

}