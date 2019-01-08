package pl.kosiorski.service;

import pl.kosiorski.dto.UserDto;

import java.util.List;

public interface UserService {

  List<UserDto> findAllUser();
}
