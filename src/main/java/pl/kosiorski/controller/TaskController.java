package pl.kosiorski.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.services.TaskService;

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
  public List<TaskDto> getList() {
    return taskService.getAllTasks();
  }

  @GetMapping("/{id}")
  public TaskDto getOne(@PathVariable Long id) {
    return taskService.findById(id);
  }

  @PostMapping("")
  public TaskDto save(@RequestBody @Valid TaskDto taskDto) {

    taskService.saveTask(taskDto);
    return taskDto;
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    return taskService.removeById(id);
  }

  @PutMapping("/{id}")
  public TaskDto uptade(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto){
      return taskService.updateById(id);
  }
}
