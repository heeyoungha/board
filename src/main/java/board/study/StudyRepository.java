package board.study;

import board.study.type.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long>, RevisionRepository<Study, Long, Long> {

    Optional<Study> findByStudyType(StudyType studyType);
}
