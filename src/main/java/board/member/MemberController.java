package board.member;

import board.TimeEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "member v1 API", description = "회원를 관리하는 API")
public class MemberController extends TimeEntity {

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/member")
    @Operation(summary = "회원을 생성합니다 ➕ ")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request){
        MemberResponse response = memberService.createMember(request);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/member")
    @Operation(summary = "회원 리스트를 전부 조회합니다 🔍 [ ]")
    public ResponseEntity<List<MemberResponse>> readMemberList(){
        List<MemberResponse> members = memberService.readMemberList();
        return ResponseEntity.ok(members);
    }

    @ResponseBody
    @GetMapping("/member/{id}")
    @Operation(summary = "특정 회원을 조회합니다 🔍 M ")
    public ResponseEntity<MemberResponse> readMember(@PathVariable Long id){
        MemberResponse response = memberService.readMember(id);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @DeleteMapping("/member/{id}")
    @Operation(summary = "특정 회원을 삭제합니다 🗑 ")
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseBody
    @GetMapping("/member/{id}/average-bookmark")
    @Operation(summary = "회원 평균 북마크를 조회합니다 🔍🔰")
    public ResponseEntity<MBookmarkResponse> getMemberAverageBookmark(@PathVariable Long id){
        MBookmarkResponse response = memberService.getBookmarkAverageBookmark(id);

        return ResponseEntity.ok(response);
    }
}
