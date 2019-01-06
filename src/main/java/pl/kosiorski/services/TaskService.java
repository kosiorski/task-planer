package pl.kosiorski.services;

import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.model.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(TaskDto taskDto);

    List<TaskDto>getAllTasks();

    TaskDto findById(Long id);

    String removeById(Long id);

    TaskDto updateById(Long id);


}
