package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private  Long id;

  @NotBlank private String content;

  @CreationTimestamp private LocalDateTime created;

  @ManyToOne
  @JoinColumn(name = "task_id")
  private Task task;
}
