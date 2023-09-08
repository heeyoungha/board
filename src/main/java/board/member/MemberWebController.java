package board.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberWebController {

    private final MemberService memberService;

    @GetMapping("/v1/member/check-email-token")
    public String checkEmailToken(String token, Long id, Model model){
       return memberService.checkEmailToken(token,id,model);
    }

}
