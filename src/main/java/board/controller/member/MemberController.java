package board.controller.member;

import board.domain.TimeEntity;
import board.dto.member.MemberRequest;
import board.dto.member.MemberResponse;
import board.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "member v1 API")
public class MemberController extends TimeEntity {

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/member")
    @Operation(summary = "멤버를 생성합니다")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request){
        MemberResponse response = memberService.createMember(request);
        return ResponseEntity.ok(response);
    }

}
