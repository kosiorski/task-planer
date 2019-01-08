package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    boolean removeById(Long id);



//
//    @Query("SELECT c FROM Category c JOIN c.user u WHERE u.id=:id")
//    List<Category> findAllByUserId(@Param("id")Long id);


//    List<Category> findAllByUserId(Long id);

    List<Category> findAllByUser(User user);

    List<Category> findAllByUserId(Long id);



}
