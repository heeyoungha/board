package board.board.repository;

import board.board.domain.Board;
import board.board.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByBoardAndId(Board board, Long aLong);
}
