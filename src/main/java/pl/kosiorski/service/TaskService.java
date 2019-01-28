package pl.kosiorski.service;

import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.User;

import java.util.List;

public interface TaskService {

  TaskDto save(User user, TaskDto taskDto);

  List<TaskDto> findAllByUserToken(String token);

  TaskDto findOneByUserAndTaskId (String token, Long taksId);
  //
  //  List<TaskDto> getAll();
  //
  //  TaskDto findById(Long id);
  //
  //  String removeById(Long id);
  //
  //  TaskDto updateById(Long id, TaskDto taskDto);
}
