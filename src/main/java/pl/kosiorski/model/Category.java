package pl.kosiorski.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;

  @NotBlank private String name;

  @CreationTimestamp private LocalDateTime created;

  @UpdateTimestamp private LocalDateTime updated;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
