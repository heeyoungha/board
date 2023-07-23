package board.board.controller;

import board.board.domain.Reply;
import board.board.dto.BoardDto;
import board.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "board v1 API", description = "게시판 관리하는 API")
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = {"/boardList","/"})
    @Operation(summary = "게시판 페이지를 조회합니다 🔍 📋 ")
    public String list(Model model,
                       @PageableDefault(page = 0, size = 10, sort = "id",
                               direction = Sort.Direction.DESC)
                       Pageable pageable,
                       String searchKeyword){
        //검색
        Page<BoardDto> list = null;

        if(searchKeyword == null){
            // 검색키워드가 없을때 : 기존의 리스트보여줌
            list = boardService.getBoardList(pageable);
        }else{
            //*검색키워드가 있을때 : 검색리스트반환
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("boardList" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "getBoardList";
    }



    @GetMapping("/board")
    @Operation(summary = "게시판 생성 페이지를 조회합니다 🔍 ➕ 📄 ")
    public String getCreateBoard(){
        return "create-board.html";
    }

    @PostMapping("/board")
    @Operation(summary = "게시글을 생성합니다 ➕ ")
    public String createBoard(BoardDto dto) {
        boardService.savePost(dto);
        return "redirect:/boardList";
    }

    @GetMapping("/board/{boardId}")
    @Operation(summary = "특정 게시글을 조회합니다 🔍 📄 ")
    public String detail(@PathVariable("boardId") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        List<Reply> replyList = boardService.getReplyList(id);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("replyList", replyList);

        return "board-detail";
    }

    @GetMapping("/board/edit/{boardId}")
    @Operation(summary = "게시글 수정페이지를 조회합니다 🔍 ♻️ ")
    public String editBoard(@PathVariable("boardId") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return "board/edit";
    }

//    @PostMapping("/board/edit/{boardId}")
//    public String editBoard(BoardDto dto){
//        boardService.savePost(dto);
//        return "/board/edit/{boardId}";
//    }

    @PostMapping(value = "/board/edit/{boardId}")
    @Operation(summary = "게시글 수정페이지를 생성합니다 ➕ ♻️ ")
    public String editBoard(@PathVariable("boardId") Long boardId, BoardDto dto){
        boardService.savePost(dto);
        return "redirect:/";
    }
    @PostMapping("/board/delete")
    @Operation(summary = "게시글을 삭제합니다 🗑 ")
    public String delete(Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }


}
