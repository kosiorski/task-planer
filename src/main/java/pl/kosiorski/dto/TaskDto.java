package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.enums.PriorityEnum;
import pl.kosiorski.model.enums.StatusEnum;

@Data
public class TaskDto {

  private Long id;
  private String name;
  private String description;
  private PriorityEnum priority;
  private StatusEnum status;
}
