package pl.kosiorski.dto;

import lombok.Data;

@Data
public class TaskDto {

  private Long id;
  private String name;
  private String description;
  private String priority;
  private String status;
}
