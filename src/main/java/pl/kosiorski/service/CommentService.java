package pl.kosiorski.service;

import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.dto.TaskDto;

import java.util.List;

public interface CommentService {

  CommentDto save(CommentDto commentDto, Long taskId);

  void deleteById(Long id) throws EmptyResultDataAccessException;

  List<CommentDto> findAllByTask(TaskDto taskDto);

  CommentDto update(CommentDto commentDto);
}
