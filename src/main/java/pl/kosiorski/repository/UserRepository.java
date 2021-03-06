package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByLogin(String login);

  User findByToken(String token);

  boolean existsByLogin(String login);

  boolean existsByToken(String token);
}
