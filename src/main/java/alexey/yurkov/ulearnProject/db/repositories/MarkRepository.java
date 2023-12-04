package alexey.yurkov.ulearnProject.db.repositories;

import alexey.yurkov.ulearnProject.db.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
