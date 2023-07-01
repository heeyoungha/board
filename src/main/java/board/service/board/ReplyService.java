package board.service.board;

import board.domain.board.Board;
import board.domain.board.Reply;
import board.domain.board.User;
import board.dto.board.ReplyDto;
import board.exception.DomainException;
import board.repository.board.BoardRepository;
import board.repository.board.ReplyRepository;
import board.repository.board.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    @Autowired
    private final ReplyRepository replyRepository;

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserRepository userRepository;

    public List<Reply> createReply(String content, User userRe, Long boardId){

//        User user = userRepository.findById(userRe.getId())
//                .orElseThrow(()-> DomainException.notFindRow(userRe.getId()));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> DomainException.notFindRow(boardId));

        Reply reply = Reply.builder()
                .board(board)
                //.user(user)
                .content(content)
                .build();
        replyRepository.save(reply);

        List<Reply> replyList = board.getReplyList();

        return replyList;
    }

    public void replyDelete(Long replyId){
        replyRepository.deleteById(replyId);
    }


    public ReplyDto getReply(Long replyId) {
        // 리포지토리를 사용하여 replyId에 해당하는 댓글 정보를 가져오는 로직 구현
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> DomainException.notFindRow(replyId));
        //ifPresent -> 오류발생
//        replyRepository.findById(replyId)).ifPresent(origin -> {
//            origin.setReplyText(reply.getReplyText());
//            replyRepo.save(origin);
//        });
//        WebBoard board = new WebBoard();
//        board.setBno(bno);

        // 필요한 데이터를 ReplyDto 형태로 변환하여 반환
        ReplyDto replyDto = ReplyDto.builder()
                .reply(reply)
                .build();
        return null;
    }

    public void editReply(Long boardId, Long replyId, String content) {
        // 리포지토리를 사용하여 replyId에 해당하는 댓글 정보를 가져오고,
        Board board = boardRepository.findById(boardId).orElseThrow(()->DomainException.notFindRow(boardId));
        Reply reply = replyRepository.findByBoardAndId(board, replyId).orElseThrow(()-> DomainException.notFindRow(replyId));
        // editedReplyDto의 내용으로 댓글을 수정하는 로직 구현
        reply.updateReply(content);
    }

}
