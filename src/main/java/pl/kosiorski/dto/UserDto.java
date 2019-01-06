package pl.kosiorski.dto;

import lombok.Data;
import pl.kosiorski.model.User;

@Data
public class UserDto {


    private String login;

    private String password;

    private String email;

    private String active;

    public User toUser() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    public User toSave() {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setActive(true);

        return user;
    }




}
