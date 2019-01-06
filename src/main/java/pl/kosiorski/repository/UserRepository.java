package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
