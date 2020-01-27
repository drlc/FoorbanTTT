package it.daraloca.challenge.tttfoorban.service;

import java.util.List;
import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.daraloca.challenge.tttfoorban.dto.GameDTO;

/**
 * GameSrv
 */
@Service
public class GameSrv {

	public List<GameDTO> findAll(Predicate predicate, Pageable pageable) {
		return null;
	}

	public GameDTO findOne(UUID gameId) {
		return null;
	}

	public UUID create(GameDTO game) {
		return null;
	}

}