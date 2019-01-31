package pl.kosiorski.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import pl.kosiorski.dto.CommentDto;
import pl.kosiorski.model.Comment;

@Component
public class CommentMapper extends ConfigurableMapper {

  protected void configure(MapperFactory factory) {

    factory
        .classMap(Comment.class, CommentDto.class)
        .field("id", "id")
        .field("content", "content")
        .field("created", "created")
        .field("created", "created")
        .byDefault()
        .register();
  }
}
