package board.study;

import board.study.type.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByStudyType(StudyType studyType);
}
