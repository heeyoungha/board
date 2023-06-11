package board.controller;

import board.domain.Reply;
import board.domain.User;
import board.dto.ReplyDto;
import board.service.ReplyService;
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
public class ReplyController {

    @Autowired
    private final ReplyService replyService;

    @PostMapping("/board/{boardId}/reply")
    public String createReply(@PathVariable("boardId") Long boardId,
                              @RequestParam("content") String content,
                              //name 속성에는 세션에 저장된 속성의 이름을, required 속성에는 해당 속성이 반드시 필요한지 여부를 지정
                              @SessionAttribute(name = "user", required = false) User user, Model model){
        List<Reply> replyList = replyService.createReply(content, user, boardId);
        model.addAttribute("replyList", replyList);
        return "redirect:/board/{boardId}";
    }

    @PutMapping("/{bno}")
    public List<Reply> updateReply(@PathVariable("bno")Long bno, @RequestBody Reply reply){
//
//        replyService.updateReply();
//
//        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
        return null;
    }

    // 댓글 수정 페이지로 이동
    @GetMapping("/board/{boardId}/reply/{replyId}/edit")
    public String editReplyPage(@PathVariable Long boardId, @PathVariable Long replyId, Model model) {
        ReplyDto replyDto = replyService.getReply(replyId);
        model.addAttribute("reply", replyDto);
        return "edit-reply";
    }

    // 댓글 수정 처리
    @PutMapping("/board/{boardId}/reply/{replyId}")
    @ResponseBody
    public ResponseEntity<String> editReply(@PathVariable Long boardId, @PathVariable Long replyId, @RequestBody ReplyDto editedReplyDto) {
        try {
            replyService.editReply(replyId, editedReplyDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//Delete어노테이션 사용 시 "Request method 'DELETE' not supported]" 오류 뜸
//    @DeleteMapping("/board/{boardId}/reply/{replyId}")
//    public String deleteReply(@PathVariable Long boardId, @PathVariable Long replyId) {
//        replyService.replyDelete(replyId);
//        return "redirect:/board/{boardId}";
//    }

    @RequestMapping(value = "/board/{boardId}/reply/{replyId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String>  deleteReply(@PathVariable Long boardId, @PathVariable Long replyId) {
        replyService.replyDelete(replyId);
        return ResponseEntity.ok("success");
        //return "redirect:/board/{boardId}";
    }

}
