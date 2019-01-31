package pl.kosiorski.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.model.Comment;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.mapper.CommentMapper;
import pl.kosiorski.model.mapper.TaskMapper;
import pl.kosiorski.repository.CommentRepository;
import pl.kosiorski.repository.TaskRepository;
import pl.kosiorski.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  private final TaskRepository taskRepository;
  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;
  private final TaskMapper taskMapper;

  @Autowired
  public CommentServiceImpl(
      TaskRepository taskRepository,
      CommentRepository commentRepository,
      CommentMapper commentMapper,
      TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.commentRepository = commentRepository;
    this.commentMapper = commentMapper;
    this.taskMapper = taskMapper;
  }

  @Override
  public List<CommentDto> findAllByTask(TaskDto taskDto) {
    Task task = taskMapper.map(taskDto, Task.class);

    List<Comment> allByTask = commentRepository.findAllByTask(task);
    return commentMapper.mapAsList(allByTask, CommentDto.class);
  }

  @Override
  public CommentDto save(CommentDto commentDto, Long taskId) {
    Task task = taskRepository.findById(taskId);

    if (task == null) {
      throw new ObjectNotFoundException(taskId, "Task not found");
    }
    Comment commentToSave = commentMapper.map(commentDto, Comment.class);
    commentToSave.setTask(task);
    Comment savedComment = commentRepository.save(commentToSave);

    return commentMapper.map(savedComment, CommentDto.class);
  }

  @Override
  @Transactional
  public void deleteById(Long id) throws EmptyResultDataAccessException {
    commentRepository.deleteById(id);
  }
}
