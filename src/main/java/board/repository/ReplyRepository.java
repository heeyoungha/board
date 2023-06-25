package board.repository;

import board.domain.Board;
import board.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByBoardAndId(Board board, Long aLong);
}
