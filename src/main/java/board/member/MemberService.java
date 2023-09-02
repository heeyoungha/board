package board.member;

import board.common.audit.AuditRevision;
import board.common.dto.HistoryResponse;
import board.common.email.event.MemberCreatedMessage;
import board.common.exception.DomainException;
import board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    private final MemberMapper memberMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

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
        Member persistMember = memberRepository.save(member);

        applicationEventPublisher.publishEvent(new MemberCreatedMessage(persistMember));

        return memberMapper.toMemberResponse(persistMember);

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

    @Transactional
    public <T> List<HistoryResponse<?>> readMemberHistoryList(Long id) {
        Revisions<Long, Member> revisions = memberRepository.findRevisions(id);
        return revisions.getContent().stream()
                .map(rev -> getHistoryResponse(rev))
                .collect(Collectors.toList());
    }

    private static <T> HistoryResponse<?> getHistoryResponse(Revision<Long, Member> rev) {


        //누가
        Member member = rev.getEntity();

        //ResponseHitory 객체
        AuditRevision auditRevision = (AuditRevision) rev.getMetadata().getDelegate();

        //무엇을
        //String revisionType = rev.getMetadata().getRevisionType().toString();

        //시간
        String time = rev.getRevisionInstant().toString().substring(9,28);

        MemberInfo memberInfo = MemberInfo.of(member);
        return HistoryResponse.from(time, auditRevision, memberInfo);
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
