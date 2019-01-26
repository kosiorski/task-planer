package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.Category;

@Data
public class CategoryDto {

  private Long id;

  private String name;

  public Category toSave() {
    Category category = new Category();
    category.setId(id);
    category.setName(name);
    return category;
  }
}
