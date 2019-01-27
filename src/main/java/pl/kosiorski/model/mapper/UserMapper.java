package pl.kosiorski.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import pl.kosiorski.dto.UserDto;
import pl.kosiorski.model.User;

@Component
public class UserMapper extends ConfigurableMapper {

  protected void configure(MapperFactory factory) {

    factory
        .classMap(User.class, UserDto.class)
        .field("id", "id")
        .field("login", "login")
        .field("email", "email")
        .byDefault()
        .register();
  }
}
