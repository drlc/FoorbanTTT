package it.daraloca.challenge.tttfoorban.data.move;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * GameRepository
 */
@Repository
public interface MoveRepository extends JpaRepository<Move, UUID>, QuerydslPredicateExecutor<Move> {

}