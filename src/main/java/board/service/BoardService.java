package board.service;

import board.domain.Board;
import board.dto.BoardDto;
import board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

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
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(dto);
        }
        //return boardDtoList;
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());

    }

    //검색
    public Page<BoardDto> boardSearchList(String searchKeyword, Pageable pageable){
        Page<Board> boards =  boardRepository.findByTitleContaining(searchKeyword, pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards){
            BoardDto dto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDtoList.add(dto);
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }

    public void savePost(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity()).getId();
    }

    public BoardDto getPost(Long id){
        Optional<Board> boardWrapper = boardRepository.findById(id);
        if(boardWrapper.isPresent())
        {
            Board board = boardWrapper.get();

            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdDate(board.getCreatedDate())
                    .build();

            return boardDto;
        }

        return null;
    }

    public void deletePost(Long id){
        Optional<Board> optBoard = boardRepository.findById(id);
        if(optBoard.isPresent()){
            Board board = optBoard.get();
            boardRepository.deleteById(id);
        }
    }

}
