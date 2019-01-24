package pl.kosiorski.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.kosiorski.dto.CategoryDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
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

  public CategoryDto toCategoryDto(){
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setName(name);
    return categoryDto;
  }


}
