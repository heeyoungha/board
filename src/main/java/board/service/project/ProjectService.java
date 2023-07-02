package board.service.project;

import board.domain.member.Member;
import board.domain.project.Project;
import board.domain.study.Study;
import board.dto.project.ProjectRequest;
import board.dto.project.ProjectResponse;
import board.exception.DomainException;
import board.repository.member.MemberRepository;
import board.repository.project.ProjectRepository;
import board.repository.study.StudyRepository;
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
