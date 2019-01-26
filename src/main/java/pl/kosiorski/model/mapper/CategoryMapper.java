package pl.kosiorski.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.model.Category;

@Component
public class CategoryMapper extends ConfigurableMapper {

  public void configure(MapperFactory factory) {
    factory
        .classMap(Category.class, CategoryDto.class)
        .field("id", "id")
        .field("name", "name")
        .byDefault()
        .register();
  }
}
