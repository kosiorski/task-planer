package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findAllByUser(User user);
}
