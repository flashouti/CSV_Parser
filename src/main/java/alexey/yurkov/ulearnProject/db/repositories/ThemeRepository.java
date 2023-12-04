package alexey.yurkov.ulearnProject.db.repositories;

import alexey.yurkov.ulearnProject.db.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository <Theme, Integer> {
}
