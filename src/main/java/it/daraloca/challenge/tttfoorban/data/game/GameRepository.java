package it.daraloca.challenge.tttfoorban.data.game;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * GameRepository
 */
@Repository
public interface GameRepository extends JpaRepository<Game, UUID>, QuerydslPredicateExecutor<Game> {

}