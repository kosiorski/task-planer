package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findByToken(String token) {
    return userRepository.findByToken(token);
  }

  @Override
  public void generateNewToken(String token) {

    User fromToken = userRepository.findByToken(token);
    fromToken.setToken(UUID.randomUUID().toString());
    userRepository.save(fromToken);
  }

}
