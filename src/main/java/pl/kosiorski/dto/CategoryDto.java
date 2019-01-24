package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.Category;

@Data
public class CategoryDto {

  private String name;


  public Category toSave() {
    Category category = new Category();
    category.setName(name);
    return category;
  }


}
