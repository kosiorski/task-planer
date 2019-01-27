package pl.kosiorski.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.service.AuthService;
import pl.kosiorski.service.TaskService;
import pl.kosiorski.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;
  private final AuthService authService;
  private final UserService userService;

  public TaskController(TaskService taskService, AuthService authService, UserService userService) {
    this.taskService = taskService;
    this.authService = authService;
    this.userService = userService;
  }

  @PostMapping
  public TaskDto save(
      @Valid @RequestBody TaskDto taskDto, @RequestHeader("Authorization") String token) {

    try {
      if (authService.validateToken(token)) {
        return taskService.save(userService.findByToken(token), taskDto);
      }

    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  @GetMapping
  public List<TaskDto> getAllByUserToken(@RequestHeader("Authorization") String token) {
    try {
      if (authService.validateToken(token)) {
        return taskService.findAllByUserToken(token);
      }
    } catch (NoAuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
    }
    return null;
  }

  //
  //  @GetMapping("")
  //  public List<TaskDto> getAll() {
  //    return taskService.getAll();
  //  }
  //
  //  @GetMapping("/{id}")
  //  public TaskDto getOne(@PathVariable Long id) {
  //    return taskService.findById(id);
  //  }
  //
  //  @PostMapping("")
  //  public TaskDto save(@RequestBody @Valid TaskDto taskDto) {
  //
  //    taskService.save(taskDto);
  //    return taskDto;
  //  }
  //
  //  @DeleteMapping("/{id}")
  //  public String delete(@PathVariable Long id) {
  //    return taskService.removeById(id);
  //  }
  //
  //  @PutMapping("/{id}")
  //  public TaskDto updade(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto){
  //      return taskService.updateById(id, taskDto);
  //  }

}
