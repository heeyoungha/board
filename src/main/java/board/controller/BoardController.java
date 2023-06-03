package board.controller;

import board.domain.Board;
import board.dto.BoardDto;
import board.repository.BoardRepository;
import board.service.BoardService;
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
public class BoardController {

    private final BoardService boardService;

    private static final int page_size = 10;

    @GetMapping("/")
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

        return "list.html";
    }



    @GetMapping("/write")
    public String write(){
        return "write.html";
    }

    @PostMapping("/write")
    public String write(BoardDto dto) {
        boardService.savePost(dto);
        return "redirect:/";
    }

    @GetMapping("/list/{no}")
    public String detail(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return "detail.html";
    }

    @GetMapping("/board/edit/{no}")
    public String edit(@PathVariable("no") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return "board/edit";
    }

    @PostMapping("/board/edit")
    public String edit(BoardDto dto){
        boardService.savePost(dto);
        return "redirect:/";
    }

    @PostMapping("/board/delete")
    public String delete(Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }


}
