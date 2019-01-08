package pl.kosiorski.service;

import pl.kosiorski.dto.UserDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;

public interface AuthService {

  UserDto registerUser(UserDto userDto) throws UserAlreadyExistsException;

  boolean loginUser(UserDto userDto) throws UserNotFoundException;

  boolean valid(String tokenFromHeader) throws NoAuthorizationException;
}
