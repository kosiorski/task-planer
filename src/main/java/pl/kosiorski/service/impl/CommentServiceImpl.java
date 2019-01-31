package pl.kosiorski.service.impl;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.model.Comment;
import pl.kosiorski.model.Task;
import pl.kosiorski.model.User;
import pl.kosiorski.model.mapper.CommentMapper;
import pl.kosiorski.model.mapper.TaskMapper;
import pl.kosiorski.repository.CommentRepository;
import pl.kosiorski.repository.TaskRepository;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  private final TaskRepository taskRepository;
  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;
  private final TaskMapper taskMapper;
  private final UserRepository userRepository;

  @Autowired
  public CommentServiceImpl(
      TaskRepository taskRepository,
      CommentRepository commentRepository,
      CommentMapper commentMapper,
      TaskMapper taskMapper,
      UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.commentRepository = commentRepository;
    this.commentMapper = commentMapper;
    this.taskMapper = taskMapper;
    this.userRepository = userRepository;
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
    commentToSave.setUser(task.getUser());
    Comment savedComment = commentRepository.save(commentToSave);

    return commentMapper.map(savedComment, CommentDto.class);
  }

  @Override
  public CommentDto update(User user, CommentDto commentDto) {
    if (commentDto != null) {

      Comment commentToUpdate = commentMapper.map(commentDto, Comment.class);
      commentToUpdate.setUser(user);
      Comment updated = commentRepository.save(commentToUpdate);
      return commentMapper.map(updated, CommentDto.class);
    }
    return null;
  }

  @Override
  @Transactional
  public void deleteById(Long id) throws EmptyResultDataAccessException {
    commentRepository.deleteById(id);
  }

  @Override
  public boolean commentBelongToUserAndTask(String userToken, Long taskId, Long commentId)
      throws NoAuthorizationException {
    User user = userRepository.findByToken(userToken);
    Task task = taskRepository.findById(taskId);
    Comment comment = commentRepository.findById(commentId).get();

    if (task.getUser().getId().equals(user.getId())
        && comment.getUser().getId().equals(user.getId())) {
      return true;
    } else {
      throw new NoAuthorizationException("You dont have authorization");
    }
  }
}
