package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.UserDto;
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
  public boolean loginUser(UserDto userDto) throws UserNotFoundException {

    boolean result = false;
    User fromDto = userDto.toSave();
    User userByLogin;

    try {
      userByLogin = userRepository.findByLogin(userDto.getLogin());

      boolean isPassCorrect = bCryptPasswordEncoder.matches(fromDto.getPassword(), userByLogin.getPassword());
      boolean isLoginCorrect = userByLogin.getLogin().equals(userDto.getLogin());

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
  public UserDto registerUser(UserDto userDto) throws UserAlreadyExistsException {

    User user = userDto.toSave();

    if (!userRepository.existsByLogin(userDto.getLogin())) {

      user.setToken(UUID.randomUUID().toString());
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      user.setActive(true);
      userRepository.save(user);


    } else {
      throw new UserAlreadyExistsException("User with that login already exists");
    }
    return user.toUserDto();
  }
}
