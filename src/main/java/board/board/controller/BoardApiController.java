package board.board.controller;

import board.board.domain.Reply;
import board.board.dto.BoardDto;
import board.board.dto.BoardDtoReplyList;
import board.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/board")
@RequiredArgsConstructor
@Tag(name = "board v1 API", description = "게시판 관리하는 API")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    @Parameter(in = ParameterIn.QUERY
            , description = "페이지 번호이며 0부터 시작 됩니다. (0..N)"
            , name = "page"
            , schema = @Schema(type = "integer", defaultValue = "0"))
    @Parameter(in = ParameterIn.QUERY
            , description = "페이지의 응답받을 크기입니다."
            , name = "size"
            , schema = @Schema(type = "integer", defaultValue = "10"))
    @Parameter(in = ParameterIn.QUERY
            , description = "조회시 최신순 정렬할 속성을 의미합니다."
            , name = "sort"
            , schema = @Schema(type = "string"))
    @Operation(summary = "게시판 페이지를 조회합니다 🔍 📋 ")
    public Page<BoardDto> list(
                       @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                       Pageable pageable,
                       @PathVariable String searchKeyword
    ){
        return boardService.boardSearchList(searchKeyword, pageable);
    }

    /*@GetMapping("/")
    @Operation(summary = "게시판 생성 페이지를 조회합니다 🔍 ➕ 📄 ")
    public String getCreateBoard(){
        return "create-board.html";
    }*/

    @PostMapping("/")
    @Operation(summary = "게시글을 생성합니다 ➕ ")
    public BoardDto createBoard(@RequestBody BoardDto dto) {
        return boardService.savePost(dto);
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "특정 게시글을 조회합니다 🔍 📄 ")
    public BoardDtoReplyList detail(@PathVariable("boardId") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        List<Reply> replyList = boardService.getReplyList(id);
        BoardDtoReplyList boardDtoReplyList = boardService.getBdtoRlist(boardDto, replyList);
        model.addAttribute("boardDtoReplyList", boardDtoReplyList);
        return boardDtoReplyList;
    }

    @PatchMapping(value = "/edit/{boardId}")
    @Operation(summary = "특정 게시글을 수정합니다 ♻️ ")
    public ResponseEntity<Void> editBoard(@PathVariable("boardId") Long boardId,
                                          @RequestBody BoardDto dto){
        boardService.savePost(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/delete")
    @Operation(summary = "게시글을 삭제합니다 🗑 ")
    public ResponseEntity<Void> delete(Long id){
        boardService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
