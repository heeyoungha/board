package board.board.controller;

import board.board.domain.Reply;
import board.board.domain.User;
import board.board.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "reply V1 API", description = "댓글을 관리하는 API")
public class ReplyController {

    @Autowired
    private final ReplyService replyService;

    @PostMapping("/board/{boardId}/reply")
    @Operation(summary = "댓글을 생성합니다 ➕ ")
    public String createReply(@PathVariable("boardId") Long boardId,
                              @RequestParam("content") String content,
                              //name 속성에는 세션에 저장된 속성의 이름을, required 속성에는 해당 속성이 반드시 필요한지 여부를 지정
                              @SessionAttribute(name = "user", required = false) User user, Model model){
        List<Reply> replyList = replyService.createReply(content, user, boardId);
        model.addAttribute("replyList", replyList);
        return "redirect:/board/{boardId}";
    }

//    @PutMapping("/{bno}")
//    @Operation(summary = "댓글을 수정합니다 ♻️ ")
//    public List<Reply> updateReply(@PathVariable("bno")Long bno, @RequestBody Reply reply){
////
////        replyService.updateReply();
////
////        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
//        return null;
//    }


    // 댓글 수정 처리
    @PutMapping("/board/{boardId}/reply/{replyId}")
    @ResponseBody
    @Operation(summary = "댓글을 수정합니다 ♻️ ")
    public ResponseEntity<String> editReply(@PathVariable("boardId") Long boardId,
                                            @PathVariable("replyId") Long replyId,
                                            @RequestParam("content") String content) {
        try {
            replyService.editReply(boardId, replyId, content);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @RequestMapping(value = "/board/{boardId}/reply/{replyId}", method = RequestMethod.DELETE)
    @ResponseBody
    @Operation(summary = "댓글을 삭제합니다 🗑 ")
    public ResponseEntity<String>  deleteReply(@PathVariable Long boardId, @PathVariable Long replyId) {
        replyService.replyDelete(replyId);
        return ResponseEntity.ok("success");
        //return "redirect:/board/{boardId}";
    }

}
