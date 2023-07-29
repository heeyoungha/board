package board.member;

import board.exception.DomainException;
import board.member.type.Address;
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
                .pw(request.getPw())
                .age(request.getAge())
                .username(request.getUsername())
                .interest(request.getInterest())
                .address(new Address(request.getAddress1(),request.getAddress2()))
                .build();
        memberRepository.save(member);

        return new MemberResponse(member);

    }

    @Transactional
    public List<MemberResponse> readMemberList() {
        List<MemberResponse> members = new ArrayList<>();
        members = memberRepository.findAll()
                    .stream()
                    .map(MemberResponse::new)
                    .collect(Collectors.toList());
        return members;
    }

    public MemberResponse readMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        MemberResponse response = new MemberResponse(member);

        return response;
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        member.delete();
    }

    @Transactional
    public MBookmarkResponse getBookmarkAverageBookmark(Long id){

        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        return MBookmarkResponse.builder()
                .bookmark(member.getBookmarkAverageBookmark())
                .userName(member.getUsername())
                .build();
    }
}
