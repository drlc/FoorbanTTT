package it.daraloca.challenge.tttfoorban.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.daraloca.challenge.tttfoorban.dto.MoveDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveResultDTO;

/**
 * MoveSrv
 */
@Service
public class MoveSrv {

	public List<MoveDTO> findAll(UUID gameId) {
		return null;
	}

	public MoveResultDTO move(UUID gameId, MoveDTO move) {
		return null;
	}

}