package pl.kosiorski.service;

import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.CommentDto;

public interface CommentService {

  CommentDto save(CommentDto commentDto, Long taskId);

  void deleteById(Long id) throws EmptyResultDataAccessException;

}
