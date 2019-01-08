package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.AuthService;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthServiceImpl(
      BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public boolean valid(String tokenFromHeader) throws NoAuthorizationException {

    try {
      User userByToken = userRepository.findByToken(tokenFromHeader);
      String newToken = UUID.randomUUID().toString();
      userByToken.setToken(newToken);
      userRepository.save(userByToken);
      return true;

    } catch (Exception exception) {
      throw new NoAuthorizationException("You dont have authorization, try to login");
    }
  }

  @Override
  public boolean loginUser(User user) throws UserNotFoundException {

    boolean result = false;
    User userByLogin;

    try {
      userByLogin = userRepository.findByLogin(user.getLogin());

      boolean isPassCorrect =
          bCryptPasswordEncoder.matches(user.getPassword(), userByLogin.getPassword());
      boolean isLoginCorrect = userByLogin.getLogin().equals(user.getLogin());

      if (isPassCorrect && isLoginCorrect) {
        userByLogin.setToken(UUID.randomUUID().toString());
        userRepository.save(userByLogin);

        result = true;
      }

    } catch (Exception exception) {
      throw new UserNotFoundException("Incorrect data or user does not exist");
    }
    return result;
  }

  @Override
  public User registerUser(User user) throws UserAlreadyExistsException {

    try {
      if (!userRepository.existsByLogin(user.getLogin())) {
        user.setToken(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
      }
    } catch (Exception exception) {
      throw new UserAlreadyExistsException("User with that login already exists");
    }
    return user;
  }
}
