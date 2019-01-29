package pl.kosiorski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kosiorski.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {}
