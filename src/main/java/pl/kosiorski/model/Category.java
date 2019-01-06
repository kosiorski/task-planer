package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Long id;

  @NotBlank private String name;

  @CreationTimestamp private LocalDateTime created;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


}
