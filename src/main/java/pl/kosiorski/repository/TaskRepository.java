package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, User> {

    Task findById(Long id);

    boolean removeById(Long id);


}
