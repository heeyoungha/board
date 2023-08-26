package board.member;

import board.common.dto.HistoryResponse;
import board.common.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
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
//                .address(new Address(request.getAddress1(),request.getAddress2())
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

    @Transactional
    public List<MemberResponse> readMemberHistoryList(Long id) {
        Revisions<Long, Member> revisions = memberRepository.findRevisions(id);

        return revisions.getContent().stream()
                .map(rev -> getHistoryResponse(rev))
                .collect(Collectors.toList());
    }

    private static HistoryResponse getHistoryResponse(Revision<Long, Member> rev) {

        //누가
        //무엇을
        //시간


        Member member = rev.getEntity();
        return new HistoryResponse(entity);
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
