package board.repository.study;

import board.domain.study.Study;
import board.domain.study.type.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByStudyType(StudyType studyType);
}
