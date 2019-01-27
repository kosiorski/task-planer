package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, User> {

  List<Task> findByUser(User user);
}
