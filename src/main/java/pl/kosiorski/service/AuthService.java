package pl.kosiorski.service;

import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;
import pl.kosiorski.model.User;

public interface AuthService {

  User registerUser(User user) throws UserAlreadyExistsException;

  boolean loginUser(User user) throws UserNotFoundException;

  boolean valid(String token) throws NoAuthorizationException;
}
