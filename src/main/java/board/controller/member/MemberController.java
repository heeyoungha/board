package board.controller.member;

import board.domain.TimeEntity;
import board.dto.member.MemberRequest;
import board.dto.member.MemberResponse;
import board.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseBody
    @GetMapping("/member")
    @Operation(summary = "멤버를 전부 조회합니다")
    public ResponseEntity<List<MemberResponse>> readMemberList(){
        List<MemberResponse> members = memberService.readMemberList();
        return ResponseEntity.ok(members);
    }

}
