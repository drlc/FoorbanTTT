package it.daraloca.challenge.tttfoorban.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.dozer.Mapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import it.daraloca.challenge.tttfoorban.data.game.Game;
import it.daraloca.challenge.tttfoorban.data.game.GameRepository;
import it.daraloca.challenge.tttfoorban.data.move.Move;
import it.daraloca.challenge.tttfoorban.data.move.MoveRepository;
import it.daraloca.challenge.tttfoorban.dto.MoveDTO;
import it.daraloca.challenge.tttfoorban.dto.MoveResultDTO;
import it.daraloca.challenge.tttfoorban.enums.GamerEnum;

/**
 * MoveSrvTest
 */
@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class MoveSrvTest {

    @Autowired
    @InjectMocks
    private MoveSrv moveSrv;

    @Autowired
    private MoveRepository moveRepository;
    @Autowired
    private GameRepository gameRepository;

    @AfterEach
    public void resetMock() {
    }

    @DisplayName("When call a move given a not existing game then throws an error")
    @Test
    public void moveInNoGame() {
        assertThrows(RuntimeException.class, () -> {
            moveSrv.move(UUID.randomUUID(), new MoveDTO());
        });
    }

    @DisplayName("When call a move given a terminated game then return the winner")
    @Test
    public void terminatedGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game.setWinner(GamerEnum.SEVEN);
        UUID gameID = gameRepository.save(game).getId();
        MoveResultDTO res = moveSrv.move(gameID, new MoveDTO());
        assertEquals(res.getNextToMove(), GamerEnum.SEVEN);
        assertTrue(res.getIsWinner());
    }

    @DisplayName("When call a move given a not terminated game then return the next player to play")
    @Test
    public void checkNotWinnerGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(1, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 1, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 2, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 3, GamerEnum.ONE, new Date(), game));
        // moves.add(new Move(1, 4, GamerEnum.ONE, new Date()));
        moves.add(new Move(2, 4, GamerEnum.ONE, new Date(), game));
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(2, 4, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.THREE);
        assertFalse(res.getIsWinner());
    }

    @DisplayName("When call a move given a not ended game then return the winner col")
    @Test
    public void columnWinnerGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(1, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 1, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 2, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 3, GamerEnum.ONE, new Date(), game));
        // moves.add(new Move(1, 4, GamerEnum.ONE, new Date()));
        moves.add(new Move(2, 4, GamerEnum.ONE, new Date(), game));
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(1, 4, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.ONE);
        assertTrue(res.getIsWinner());
    }

    @DisplayName("When call a move given a ended game then return the winner row")
    @Test
    public void rowWinnerGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(1, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(2, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(3, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(4, 0, GamerEnum.ONE, new Date(), game));
        // moves.add(new Move(1, 4, GamerEnum.ONE, new Date()));
        moves.add(new Move(2, 4, GamerEnum.ONE, new Date(), game));
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(0, 0, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.ONE);
        assertTrue(res.getIsWinner());
    }

    @DisplayName("When call a move given a not ended game then return the winner diagonal")
    @Test
    public void diagonalWinnerGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(0, 0, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 1, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(2, 2, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(3, 3, GamerEnum.ONE, new Date(), game));
        // moves.add(new Move(1, 4, GamerEnum.ONE, new Date()));
        moves.add(new Move(2, 4, GamerEnum.ONE, new Date(), game));
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(4, 4, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.ONE);
        assertTrue(res.getIsWinner());
    }

    @DisplayName("When call a move given a not ended game then return the winner diagonal reverse")
    @Test
    public void diagonalReverseWinnerGame() {
        Game game = new Game();
        game.setDimension(5);
        game.setNumPlayer(2);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(0, 4, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(1, 3, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(2, 2, GamerEnum.ONE, new Date(), game));
        moves.add(new Move(3, 1, GamerEnum.ONE, new Date(), game));
        // moves.add(new Move(1, 4, GamerEnum.ONE, new Date()));
        moves.add(new Move(2, 4, GamerEnum.ONE, new Date(), game));
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(4, 0, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.ONE);
        assertTrue(res.getIsWinner());
    }

    @DisplayName("When call a move given a not ended game then return the next to move")
    @Test
    public void noWinner() {
        Game game = new Game();
        game.setDimension(11);
        game.setNumPlayer(4);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(4, 0, GamerEnum.ONE, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.THREE);
        assertFalse(res.getIsWinner());
    }

    @DisplayName("When call a move with the last player given a not ended game then return the next to move is the first player")
    @Test
    public void noWinnerModuleCase() {
        Game game = new Game();
        game.setDimension(11);
        game.setNumPlayer(4);
        game = gameRepository.save(game);
        List<Move> moves = new ArrayList<>();
        moves.forEach(el -> {
            moveRepository.save(el);
        });
        MoveResultDTO res = moveSrv.move(game.getId(), new MoveDTO(4, 0, GamerEnum.ELEVEN, new Date()));
        assertEquals(res.getNextToMove(), GamerEnum.ONE);
        assertFalse(res.getIsWinner());
    }

}