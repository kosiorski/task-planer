package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import pl.kosiorski.dto.TaskDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Long id;

  @NotBlank private String name;

  @NotBlank private String description;

  @CreationTimestamp private LocalDateTime created;

  private String priority;

  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "task")
  private List<Comment> comments;



  public TaskDto toTaskDto(){
    TaskDto taskDto = new TaskDto();
    taskDto.setName(name);
    taskDto.setDescription(description);
    return taskDto;
  }
}
