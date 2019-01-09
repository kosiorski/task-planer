package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.User;

@Data
public class UserDto {


    private String login;

    private String email;

    private String token;


    public User toUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setToken(token);
        return user;
    }

    public User toSave() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        return user;
    }




}
