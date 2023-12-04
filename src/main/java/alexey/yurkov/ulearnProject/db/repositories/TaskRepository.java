package alexey.yurkov.ulearnProject.db.repositories;

import alexey.yurkov.ulearnProject.db.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
