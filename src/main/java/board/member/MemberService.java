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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public MemberResponse createMember(MemberRequest.CreateMemberRequest request) {

        MemberRequest.CreateMemberRequest afterEncodePw = MemberRequest.CreateMemberRequest.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .pw(passwordEncoder.encode(request.getPw()))
                .age(request.getAge())
                .interest(request.getInterest())
                .address(request.getAddress())
                .build();
        Member member = memberMapper.toMember(afterEncodePw);
        member.updateToken();

        Member persistMember = memberRepository.save(member);

        applicationEventPublisher.publishEvent(new MemberCreatedMessage(persistMember));

        return memberMapper.toMemberResponse(persistMember);

    }

    //Member와 업데이트Member가 1:1 대응되는 경우 거의 없음
    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest.UpdateMemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));
        /*
        Member updateMember = memberMapper.toUpdateMember(request);

        member.updateMember(updateMember);
        */
        member.updateMember(request);
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

    @Transactional
    public String checkEmailToken(String token, Long id, Model model) {
        Member member = memberRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));

        String view = "mail/checked-email";

        if(!member.getToken().equals(token)){
            model.addAttribute("error", "wrong.email");
            return view;
        }

        //member status 변경
        member.updateStatus("valid");
        model.addAttribute("userName", member.getUsername());
        return view;
    }
}
