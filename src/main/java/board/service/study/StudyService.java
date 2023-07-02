package board.service.study;

import board.domain.study.Study;
import board.dto.study.StudyRequest;
import board.dto.study.StudyResponse;
import board.repository.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public StudyResponse createStudy(StudyRequest request) {
        Study study = Study.builder()
                .study(request.getStudy())
                .build();

        studyRepository.save(study);

        StudyResponse response = StudyResponse.builder()
                .id(study.getId())
                .study(study.getStudy())
                .build();


        return response;
    }
}
