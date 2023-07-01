package board.repository.board;

import board.domain.board.Board;
import board.domain.board.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByBoardAndId(Board board, Long aLong);
}
