package pl.kosiorski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kosiorski.dto.UserDto;
import pl.kosiorski.exception.NoAuthorizationException;
import pl.kosiorski.exception.UserAlreadyExistsException;
import pl.kosiorski.exception.UserNotFoundException;
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
  public UserDto register(@RequestBody UserDto userDto) throws UserAlreadyExistsException {

    return authService.registerUser(userDto);
  }

  @PostMapping("/login")
  public boolean login(@RequestBody UserDto userDto) throws UserNotFoundException {

    return authService.loginUser(userDto);
  }

  @PostMapping("/valid")
  public boolean valid(@RequestHeader("Authorization") String token) throws NoAuthorizationException {

    return authService.valid(token);
  }
}
