package org.mbc.board.models.board.repository;

import org.mbc.board.models.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardJpaRepository extends JpaRepository<Board, Long> {

}
