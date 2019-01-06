package pl.kosiorski.services;

import pl.kosiorski.dto.UserDto;

import java.util.List;

public interface UserService {

  List<UserDto> findAllUser();
}
