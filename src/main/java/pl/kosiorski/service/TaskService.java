package pl.kosiorski.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.User;

import java.util.List;

public interface TaskService {

  TaskDto save(User user, TaskDto taskDto);

  List<TaskDto> findAllByUserToken(String token);

  TaskDto findOneById(Long taksId) throws ObjectNotFoundException;

  TaskDto update(TaskDto taskDto, User user);

  void deleteById(Long id) throws EmptyResultDataAccessException;

  boolean taskBelongToUser (String userToken, Long taskId) throws NoAuthorizationException;

}
