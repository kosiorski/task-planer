package pl.kosiorski.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TaskDto {

  private String name;
  private String description;
}
