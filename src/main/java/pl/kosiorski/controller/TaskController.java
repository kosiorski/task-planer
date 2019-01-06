package pl.kosiorski.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @
    public List<TaskDto> getList(){
        return taskService.findAllTasks();

    }
}
