package board.service.study;

import board.domain.study.Study;
import board.dto.study.StudyRequest;
import board.dto.study.StudyResponse;
import board.exception.DomainException;
import board.repository.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .studyType(study.getStudyType())
                .build();


        return response;
    }

//    @Transactional
    public List<StudyResponse> readStudyList() {
        List<StudyResponse> responses = new ArrayList<>();

        responses = studyRepository.findAll().stream()
                .map(StudyResponse::toStudyResponse)
                .collect(Collectors.toList());

        return responses;
    }

    @Transactional
    public StudyResponse readStudy(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));

        StudyResponse response = StudyResponse.builder()
                .id(study.getId())
                .studyType(study.getStudyType())
                .build();
        return response;
    }

    @Transactional
    public void deleteStudy(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()-> DomainException.notFindRow(id));

        study.delete();
    }
}
