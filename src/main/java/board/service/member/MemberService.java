package board.service.member;

import board.domain.member.Member;
import board.dto.member.MemberRequest;
import board.dto.member.MemberResponse;
import board.exception.DomainException;
import board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<MemberResponse> readMemberList() {
        List<MemberResponse> members = new ArrayList<>();
        members = memberRepository.findAll()
                    .stream()
                    .map(MemberResponse::toMemberResponse)
                    .collect(Collectors.toList());
        return members;
    }

    public MemberResponse readMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        MemberResponse response = MemberResponse.toMemberResponse(member);

        return response;
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        member.delete();
    }
}
