package pl.kosiorski.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import pl.kosiorski.dto.TaskDto;
import pl.kosiorski.model.Task;

@Component
public class TaskMapper extends ConfigurableMapper {

  protected void configure(MapperFactory factory) {

    factory
        .classMap(Task.class, TaskDto.class)
        .field("id", "id")
        .field("name", "name")
        .field("description", "description")
        .field("priority", "priority")
        .field("status", "status")
        .byDefault()
        .register();
  }
}
