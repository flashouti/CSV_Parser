package alexey.yurkov.ulearnProject.db.repositories;

import alexey.yurkov.ulearnProject.db.entities.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
}
