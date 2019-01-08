package pl.kosiorski.service;

import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.dto.UserDto;
import pl.kosiorski.model.User;

import java.util.List;

public interface UserService {

  List<UserDto> findAllUser();

  User findByToken(String token);

}
