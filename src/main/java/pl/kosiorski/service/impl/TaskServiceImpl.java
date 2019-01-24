package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.model.Task;
import pl.kosiorski.repository.TaskRepository;
import pl.kosiorski.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task save(TaskDto taskDto) {
    Task toSave = taskDto.toSave();
    // TODO category
    // TODO current user
    toSave.setPriority("HIGH");
    toSave.setStatus("ACTIVE");
    return taskRepository.save(toSave);
  }

  @Override
  public List<TaskDto> getAll() {
    List<Task> tasks = taskRepository.findAll();
    return tasks.stream().map(Task::toTaskDto).collect(Collectors.toList());
  }

  @Override
  public TaskDto findById(Long id) {
    Task task = taskRepository.findById(id);
    return task.toTaskDto();
  }

  @Override
  public String removeById(Long id) {
    Task taskById = taskRepository.findById(id);
    taskRepository.delete(taskById);
    return "SUCCESS";
  }

  @Override
  public TaskDto updateById(Long id, TaskDto taskDto) {
    Task byId = taskRepository.findById(id);
    byId.setName(taskDto.getName());
    byId.setDescription(taskDto.getDescription());
    byId.setUpdated(LocalDateTime.now());
    taskRepository.save(byId);

    return byId.toTaskDto();
  }
}
