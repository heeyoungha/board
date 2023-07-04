package board.project;

import board.exception.DomainException;
import board.member.Member;
import board.member.MemberRepository;
import board.study.Study;
import board.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {

        Study study = studyRepository.findByStudyType(request.getStudyType())
                .orElseThrow(()-> DomainException.notFindRow(request.getStudy()));

        Member member = memberRepository.findByUsername(request.getUserName())
                .orElseThrow(()->DomainException.notFindRow(request.getUserName()));

        Project project = Project.of(request.getTitle(), request.getStartdate(), study, member);

        projectRepository.save(project);

        return new ProjectResponse(project);
    }
}
