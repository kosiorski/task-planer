package pl.kosiorski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosiorski.dto.CategoryDto;
import pl.kosiorski.dto.UserDto;
import pl.kosiorski.model.Category;
import pl.kosiorski.model.User;
import pl.kosiorski.repository.UserRepository;
import pl.kosiorski.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserDto> findAllUser() {
    return null;
  }

  @Override
  public User findByToken(String token) {
    return userRepository.findByToken(token);
  }
}
