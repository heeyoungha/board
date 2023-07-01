package board.service.member;

import board.domain.member.Member;
import board.dto.member.MemberRequest;
import board.dto.member.MemberResponse;
import board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createMember(MemberRequest request) {

        Member member = Member.builder()
                .address(request.getAddress())
                .pw(request.getPw())
                .age(request.getAge())
                .username(request.getUsername())
                .interest(request.getInterest())
                .build();
        memberRepository.save(member);
        MemberResponse response = MemberResponse.builder()
                .address(member.getAddress())
                .username(member.getUsername())
                .id(member.getId())
                .interest(member.getInterest())
                .pw(member.getPw())
                .age(member.getAge())
                .build();

        return response;

    }
}
