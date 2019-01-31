package pl.kosiorski.service;

import pl.kosiorski.dto.UserDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;
import pl.kosiorski.model.User;

public interface AuthService {

  UserDto registerUser(User user) throws UserAlreadyExistsException;

  String loginUser(User user) throws UserNotFoundException;

  boolean validateToken(String tokenFromHeader) throws NoAuthenticationException;

}
