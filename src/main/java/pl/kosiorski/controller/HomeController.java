package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.UserDto;
import pl.kosiorski.exception.NoAuthenticationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;
import pl.kosiorski.model.User;
import pl.kosiorski.service.AuthService;

@RestController
@RequestMapping("/home")
public class HomeController {

  private final AuthService authService;

  @Autowired
  public HomeController(AuthService authService) {
    this.authService = authService;
  }


  @PostMapping("/register")
  public UserDto register(@RequestBody User user) throws UserAlreadyExistsException {

    return authService.registerUser(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody User user) throws UserNotFoundException {

    return authService.loginUser(user);
  }

  @PostMapping("/valid")
  public boolean valid(@RequestHeader("Authorization") String token) throws NoAuthenticationException {

    return authService.validateToken(token);
  }
}
