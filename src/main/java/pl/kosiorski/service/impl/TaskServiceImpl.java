package pl.kosiorski.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.User;
import pl.kosiorski.model.enums.PriorityEnum;
import pl.kosiorski.model.enums.StatusEnum;
import pl.kosiorski.model.mapper.TaskMapper;
import pl.kosiorski.repository.TaskRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;
  private final UserRepository userRepository;

  @Autowired
  public TaskServiceImpl(
      TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
    this.userRepository = userRepository;
  }

  @Override
  public TaskDto save(User user, TaskDto taskDto) {

    if (taskDto != null) {
      Task task = taskMapper.map(taskDto, Task.class);
      task.setUser(user);

      task.setPriority(PriorityEnum.LOW);
      task.setStatus(StatusEnum.NOT_STARTED);

      Task taskSaved = taskRepository.save(task);
      return taskMapper.map(taskSaved, TaskDto.class);
    }
    return null;
  }

  @Override
  public TaskDto findOneById(Long taskId) throws ObjectNotFoundException {

    Task task = taskRepository.findById(taskId);
    if (task == null) {
      throw new ObjectNotFoundException(taskId, "Task not found");
    }
    return taskMapper.map(task, TaskDto.class);
  }

  @Override
  public List<TaskDto> findAllByUserToken(String token) {

    List<Task> tasks = taskRepository.findByUser(userRepository.findByToken(token));
    return taskMapper.mapAsList(tasks, TaskDto.class);
  }

  @Override
  public boolean taskBelongToUser(String userToken, Long taskId) throws NoAuthorizationException {
    User user = userRepository.findByToken(userToken);
    Task task = taskRepository.findById(taskId);

    if (task.getUser().getId().equals(user.getId())) {
      return true;
    } else {
      throw new NoAuthorizationException("You dont have authorization");
    }
  }

  @Override
  public TaskDto update(TaskDto taskDto, User user) {
    if (taskDto != null) {

      Task taskToUpdate = taskMapper.map(taskDto, Task.class);
      taskToUpdate.setUser(user);
      Task updated = taskRepository.save(taskToUpdate);
      return taskMapper.map(updated, TaskDto.class);
    }
    return null;
  }

  @Override
  @Transactional
  public void deleteById(Long id) throws EmptyResultDataAccessException {
    taskRepository.deleteById(id);
  }
}
