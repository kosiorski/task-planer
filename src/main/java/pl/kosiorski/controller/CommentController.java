package pl.kosiorski.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.service.AuthService;
import pl.kosiorski.service.CommentService;
import pl.kosiorski.service.TaskService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
public class CommentController {

  private final String HEADER_KEY = "Authorization";

  private final AuthService authService;
  private final CommentService commentService;
  private final TaskService taskService;
  private final UserService userService;

  @Autowired
  public CommentController(
      AuthService authService,
      CommentService commentService,
      TaskService taskService,
      UserService userService) {
    this.authService = authService;
    this.commentService = commentService;
    this.taskService = taskService;
    this.userService = userService;
  }

  @PostMapping("/{taskId}/comments")
  public CommentDto save(
      @RequestBody @Valid CommentDto commentDto,
      @PathVariable Long taskId,
      @RequestHeader(HEADER_KEY) String token) {

    try {
      if (authService.validateToken(token) && taskService.taskBelongToUser(token, taskId)) {
        return commentService.save(commentDto, taskId);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (ObjectNotFoundException | NoAuthorizationException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }

    return null;
  }

  @DeleteMapping("/{taskId}/comments{commentId}")
  public ResponseEntity delete(
      @PathVariable Long taskId,
      @PathVariable Long commentId,
      @RequestHeader(HEADER_KEY) String token) {
    try {
      if (authService.validateToken(token)
          && commentService.commentBelongToUserAndTask(token, taskId, commentId)) {
        commentService.deleteById(taskId);
        return new ResponseEntity(HttpStatus.OK);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Comment with the id: " + commentId + " does not exist");

    } catch (NoAuthorizationException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }

    return null;
  }

  @GetMapping("/{taskId}/comments")
  public List<CommentDto> getAllByTaskId(
      @RequestHeader(HEADER_KEY) String token, @PathVariable Long taskId) {
    try {
      if (authService.validateToken(token) && taskService.taskBelongToUser(token, taskId)) {
        return commentService.findAllByTask(taskService.findOneById(taskId));
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (NoAuthorizationException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }
    return null;
  }

  @PutMapping("/{taskId}/comments{commentId")
  public CommentDto updade(
      @Valid @RequestBody CommentDto commentDto,
      @RequestHeader(HEADER_KEY) String token,
      @PathVariable Long taskId,
      @PathVariable Long commentId) {
    try {
      if (authService.validateToken(token)
          && commentService.commentBelongToUserAndTask(token, taskId, commentId)) {
        return commentService.update(userService.findByToken(token), commentDto);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (NoAuthorizationException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }

    return null;
  }
}
