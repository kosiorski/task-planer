package pl.kosiorski.service;

import pl.kosiorski.model.User;

public interface UserService {

  User findByToken(String token);

  void generateNewToken(String token);

}
