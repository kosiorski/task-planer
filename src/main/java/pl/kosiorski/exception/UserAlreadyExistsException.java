package pl.kosiorski.exception;

public class UserAlreadyExistsException extends Exception {

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
