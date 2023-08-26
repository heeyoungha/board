package board.study;

import board.common.exception.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public StudyResponse createStudy(StudyRequest request) {
        Study study = Study.builder()
                .study(request.getStudy())
                .location(request.getLocation())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        studyRepository.save(study);
        log.info("신규스터디[{}]이/가 등록되었습니다!", request.getStudy());

        return new StudyResponse(study);
    }

//    @Transactional
    public List<StudyResponse> readStudyList() {
        List<StudyResponse> responses = studyRepository.findAll().stream()
                .map(StudyResponse::new)
                .collect(Collectors.toList());

        return responses;
    }

    @Transactional
    public StudyResponse readStudy(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));

        StudyResponse response = new StudyResponse(study);

        return response;
    }

    @Transactional
    public void deleteStudy(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));

        study.delete();
    }

    public SBookmarkResponse getAverageBookmark(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));
        return SBookmarkResponse.builder()
                .bookmark(study.getAverageBookmark())
                .studyType(study.getStudyType())
                .build();
    }
}
