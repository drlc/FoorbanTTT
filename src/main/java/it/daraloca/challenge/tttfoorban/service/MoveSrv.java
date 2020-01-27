package it.daraloca.challenge.tttfoorban.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import it.daraloca.challenge.tttfoorban.data.move.MoveRepository;
import it.daraloca.challenge.tttfoorban.data.move.QMove;
import it.daraloca.challenge.tttfoorban.dto.GameDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveResultDTO;

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
		if (move == null) {
			throw new RuntimeException("No move to save");
		}
		//if already finished, return the winner
		if (gameSrv.findOne(gameId).getWinner() != null) {
			MoveResultDTO mr = new MoveResultDTO();
			mr.setIsWinner(true);
			mr.setNextToMove(game.getWinner());
			return mr;
		}
		return null;
	}

}