package board.board.service;

import board.board.domain.Board;
import board.board.domain.Reply;
import board.board.dto.BoardDto;
import board.board.repository.BoardRepository;
import board.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public Page<BoardDto> getBoardList(Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards){
            BoardDto dto = BoardDto.builder()
                    .board(board)
                    .build();

            boardDtoList.add(dto);
        }
        //return boardRequestList;
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());

    }

    //검색
    public Page<BoardDto> boardSearchList(String searchKeyword, Pageable pageable){
        if(searchKeyword == null){
            return getBoardList(pageable);
        }

        Page<Board> boards =  boardRepository.findByTitleContaining(searchKeyword, pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards){
            BoardDto dto = BoardDto.builder()
                    .board(board)
                    .build();

            boardDtoList.add(dto);
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }

    public void savePost(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    public BoardDto getPost(Long id){
        Optional<Board> boardWrapper = boardRepository.findById(id);
        if(boardWrapper.isPresent())
        {
            Board board = boardWrapper.get();

            BoardDto boardDto = BoardDto.builder()
                    .board(board)
                    .build();

            return boardDto;
        }

        return null;
    }

    public List<Reply> getReplyList(Long boardId){

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> DomainException.notFindRow(boardId));

        List<Reply> replyList = board.getReplyList();

        return replyList;
    }


    public void deletePost(Long id){
        Optional<Board> optBoard = boardRepository.findById(id);
        if(optBoard.isPresent()){
            Board board = optBoard.get();
            boardRepository.deleteById(id);
        }
    }



}
