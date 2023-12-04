package alexey.yurkov.ulearnProject.db.repositories;

import alexey.yurkov.ulearnProject.db.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
