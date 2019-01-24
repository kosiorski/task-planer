package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.Task;

import javax.validation.constraints.NotBlank;

@Data
public class TaskDto {

  @NotBlank private String name;
  @NotBlank private String description;

  public Task toSave() {
    Task task = new Task();
    task.setName(name);
    task.setDescription(description);
    return task;
  }
}
