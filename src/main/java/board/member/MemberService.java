package board.member;

import board.common.exception.DomainException;
import board.mapper.MemberMapper;
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
    private final MemberMapper memberMapper;

    @Transactional
    public MemberResponse createMember(MemberRequest request) {

        Member member = memberMapper.toMember(request);
//        Member member = Member.builder()
//                .pw(request.getPw())
//                .age(request.getAge())
//                .username(request.getUsername())
//                .interest(request.getInterest())
//                .address(new Address(request.getAddress1(),request.getAddress2(), request.getZipcode()))
//                .build();
        memberRepository.save(member);

        return memberMapper.toMemberResponse(member);

    }

    @Transactional
    public List<MemberResponse> readMemberList() {
        List<MemberResponse> members = new ArrayList<>();
        members = memberRepository.findAll()
                    .stream()
                    .map(a->memberMapper.toMemberResponse(a))
                    .collect(Collectors.toList());
        return members;
    }

    public MemberResponse readMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        return memberMapper.toMemberResponse(member);
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
