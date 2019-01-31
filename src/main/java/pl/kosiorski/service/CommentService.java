package pl.kosiorski.service;

import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.User;

import java.util.List;

public interface CommentService {

  CommentDto save(CommentDto commentDto, Long taskId);

  void deleteById(Long id) throws EmptyResultDataAccessException;

  List<CommentDto> findAllByTask(TaskDto taskDto);

  CommentDto update(User user, CommentDto commentDto);

  boolean commentBelongToUserAndTask (String userToken, Long taskId, Long commentId) throws NoAuthorizationException;

}
