package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.TaskDto;
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
  public List<TaskDto> findAllByUserToken(String token) {

    List<Task> tasks = taskRepository.findByUser(userRepository.findByToken(token));
    return taskMapper.mapAsList(tasks, TaskDto.class);
  }

  //
  //  @Override
  //  public List<TaskDto> getAll() {
  //    List<Task> tasks = taskRepository.findAll();
  //    return tasks.stream().map(Task::toTaskDto).collect(Collectors.toList());
  //  }
  //
  //  @Override
  //  public TaskDto findById(Long id) {
  //    Task task = taskRepository.findById(id);
  //    return task.toTaskDto();
  //  }
  //
  //  @Override
  //  public String removeById(Long id) {
  //    Task taskById = taskRepository.findById(id);
  //    taskRepository.delete(taskById);
  //    return "SUCCESS";
  //  }
  //
  //  @Override
  //  public TaskDto updateById(Long id, TaskDto taskDto) {
  //    Task byId = taskRepository.findById(id);
  //    byId.setName(taskDto.getName());
  //    byId.setDescription(taskDto.getDescription());
  //    byId.setUpdated(LocalDateTime.now());
  //    taskRepository.save(byId);
  //
  //    return byId.toTaskDto();
  //  }
}
