package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    boolean removeById(Long id);


}
