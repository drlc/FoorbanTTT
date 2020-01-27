package it.daraloca.challenge.tttfoorban.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import it.daraloca.challenge.tttfoorban.data.game.Game;
import it.daraloca.challenge.tttfoorban.data.move.Move;
import it.daraloca.challenge.tttfoorban.data.move.MoveRepository;
import it.daraloca.challenge.tttfoorban.data.move.QMove;
import it.daraloca.challenge.tttfoorban.dto.GameDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveResultDTO;
import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * MoveSrv
 */
@Service
public class MoveSrv {

	@Autowired
	private MoveRepository moveRepo;
	@Autowired
	private GameSrv gameSrv;

	@Autowired
	private Mapper mapper;

	@PersistenceContext
	private EntityManager em;

	public List<MoveDTO> findAll(UUID gameId) {
		List<MoveDTO> list = new ArrayList<>();
		StreamSupport.stream(moveRepo.findAll(QMove.move.game.id.eq(gameId), Sort.by(Order.desc("date"))).spliterator(),
				false).collect(Collectors.toList()).forEach(el -> {
					list.add(mapper.map(el, MoveDTO.class));
				});
		return list;
	}

	public MoveResultDTO move(UUID gameId, MoveDTO move) {
		GameDTO game = gameSrv.findOne(gameId);
		if (move == null || game == null) {
			throw new RuntimeException("No move to save");
		}
		// if already finished, return the winner
		if (gameSrv.findOne(gameId).getWinner() != null) {
			MoveResultDTO mr = new MoveResultDTO();
			mr.setIsWinner(true);
			mr.setNextToMove(game.getWinner());
			return mr;
		}

		// save the move
		Move moveToSave = mapper.map(move, Move.class);
		moveToSave.setGame(em.getReference(Game.class, gameId));
		moveRepo.save(moveToSave);

		// check winner
		MoveResultDTO resultWin = checkWinner(gameId, move, game);
		if (resultWin != null) {
			return resultWin;
		}

		MoveResultDTO result = new MoveResultDTO();
		result.setNextToMove(GamerEnum.values()[(move.getValue().ordinal() + 1) % game.getNumPlayer()]);
		result.setIsWinner(false);
		return result;
	}

	private MoveResultDTO checkWinner(UUID gameId, MoveDTO move, GameDTO game) {
		Map<String, GamerEnum> board = new HashMap<>();
		this.findAll(gameId).forEach(el -> {
			board.put(el.getX() + "_" + el.getY(), el.getValue());
		});
		Integer x = move.getX();
		Integer y = move.getY();
		Integer n = game.getDimension();
		Boolean winner = false;
		// col
		for (int i = 0; i < n; i++) {
			if (board.get(x + "_" + i) != move.getValue())
				break;
			if (i == n - 1) {
				winner = true;
			}
		}
		// row
		if (!winner) {
			for (int i = 0; i < n; i++) {
				if (board.get(i + "_" + y) != move.getValue())
					break;
				if (i == n - 1) {
					winner = true;
				}
			}
		}

		// diag
		if (!winner && x == y) {
			for (int i = 0; i < n; i++) {
				if (board.get(i + "_" + 1) != move.getValue())
					break;
				if (i == n - 1) {
					winner = true;
				}
			}
		}

		// other diag
		if (!winner && x + y == n - 1) {
			for (int i = 0; i < n; i++) {
				if (board.get(i + "_" + ((n - 1) - i)) != move.getValue())
					break;
				if (i == n - 1) {
					winner = true;
				}
			}
		}

		if (winner) {
			gameSrv.setWinner(gameId, move.getValue());
			MoveResultDTO result = new MoveResultDTO();
			result.setIsWinner(true);
			result.setNextToMove(move.getValue());
		}
		return null;
	}

}