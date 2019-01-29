package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.model.Task;
import pl.kosiorski.repository.TaskRepository;
import pl.kosiorski.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

  private final TaskRepository taskRepository;

  @Autowired
  public CommentServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public CommentDto save(CommentDto commentDto, Task task) {

  }
}
