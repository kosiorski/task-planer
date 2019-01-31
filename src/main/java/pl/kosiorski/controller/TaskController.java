package pl.kosiorski.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.service.AuthService;
import pl.kosiorski.service.TaskService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final AuthService authService;
  private final UserService userService;
  private final String HEADER_KEY = "Authorization";

  public TaskController(TaskService taskService, AuthService authService, UserService userService) {
    this.taskService = taskService;
    this.authService = authService;
    this.userService = userService;
  }

  @GetMapping
  public List<TaskDto> getAllByUserToken(@RequestHeader(HEADER_KEY) String token) {
    try {
      if (authService.validateToken(token)) {
        return taskService.findAllByUserToken(token);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @GetMapping("/{id}")
  public TaskDto getOneByUserToken(@RequestHeader(HEADER_KEY) String token, @PathVariable Long id) {
    try {
      if (authService.validateToken(token)) {
        return taskService.findOneByUserAndTaskId(token, id);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    } catch (ObjectNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized");
    }
    return null;
  }

  @PostMapping
  public TaskDto save(
      @Valid @RequestBody TaskDto taskDto, @RequestHeader(HEADER_KEY) String token) {

    try {
      if (authService.validateToken(token)) {
        return taskService.save(userService.findByToken(token), taskDto);
      }

    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id, @RequestHeader(HEADER_KEY) String token) {
    try {
      if (authService.validateToken(token) && taskService.taskBelongToUser(token, id)) {
        taskService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);

    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task with the id: " + id + " does not exist");

    } catch (NoAuthorizationException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
    }

    return null;
  }

  @PutMapping
  public TaskDto updade(
      @Valid @RequestBody TaskDto taskDto, @RequestHeader(HEADER_KEY) String token) {
    try {
      if (authService.validateToken(token)) {
        return taskService.update(taskDto, userService.findByToken(token));
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }
}
