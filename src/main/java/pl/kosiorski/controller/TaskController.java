package pl.kosiorski.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("")
  public List<TaskDto> getAll() {
    return taskService.getAll();
  }

  @GetMapping("/{id}")
  public TaskDto getOne(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @PostMapping("")
  public TaskDto save(@RequestBody @Valid TaskDto taskDto) {

    taskService.save(taskDto);
    return taskDto;
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    return taskService.removeById(id);
  }

  @PutMapping("/{id}")
  public TaskDto updade(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto){
      return taskService.updateById(id, taskDto);
  }


}
