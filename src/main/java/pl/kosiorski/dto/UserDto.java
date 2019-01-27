package pl.kosiorski.dto;

import lombok.Data;

@Data
public class UserDto {

  private Long id;

  private String login;

  private String email;

  private String token;
}
