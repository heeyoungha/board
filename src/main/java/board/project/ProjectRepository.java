package board.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, RevisionRepository<Project, Long, Long> {
}
