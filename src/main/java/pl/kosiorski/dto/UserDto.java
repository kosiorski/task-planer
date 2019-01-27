package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.Task;

import java.util.List;

@Data
public class UserDto {

  private Long id;

  private String login;

  private String email;

  private List<Task> tasks;

  private List<Category> categories;
}
