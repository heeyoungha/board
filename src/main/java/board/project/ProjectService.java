package board.project;

import board.common.audit.AuditRevision;
import board.common.dto.HistoryResponse;
import board.common.exception.DomainException;
import board.member.Member;
import board.member.MemberRepository;
import board.study.Study;
import board.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponse createProject(ProjectRequest.CreateProjectRequest request) {

        Study study = studyRepository.findById(request.getStudyId())
                .orElseThrow(()-> DomainException.notFindRow(request.getStudyId()));

        Member member = memberRepository.findByUsername(request.getUserName())
                .orElseThrow(()->DomainException.notFindRow(request.getUserName()));

        Project project = Project.of(request.getTitle(), request.getStartDate(), study, member);

        projectRepository.save(project);

        return new ProjectResponse(project);
    }

    @Transactional
    public ProjectResponse readProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));
        ProjectResponse response = new ProjectResponse(project);

        return response;
    }

    @Transactional
    public List<ProjectResponse> readProjectList() {

        List<ProjectResponse> response = projectRepository.findAll()
                .stream()
                .map(ProjectResponse::new)
                .collect(Collectors.toList());

        return response;
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));
        project.delete();
    }

    @Transactional
    public void updateService(Long id, ProjectRequest.UpdateProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> DomainException.notFindRow(id));

        project.updateProject(request.getTitle(), request.getStartDate());


        //projectRepository.save(project);
        //return new ProjectResponse(project);
    }

    @Transactional
    public List<HistoryResponse<?>> readProjectHistoryList(Long id) {
        Revisions<Long, Project> revisions = projectRepository.findRevisions(id);
        return revisions.getContent().stream()
                .map(rev -> getHistoryResponse(rev))
                .collect(Collectors.toList());
    }
    private static <T> HistoryResponse<?> getHistoryResponse(Revision<Long, Project> rev){
        AuditRevision auditRevision = (AuditRevision) rev.getMetadata().getDelegate();
        Project project = rev.getEntity();
        String time = rev.getRevisionInstant().toString().substring(9,28);
        ProjectInfo projectInfo = ProjectInfo.of(project);
        return HistoryResponse.from(time, auditRevision, projectInfo);
    }
}
