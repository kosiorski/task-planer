package pl.kosiorski.model;

import lombok.Data;
import pl.kosiorski.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private  Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank
    private String password;

    @Email
    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    private boolean active;

    public UserDto toUserDto() {
        UserDto user = new UserDto();

        user.setLogin(login);
        user.setEmail(email);

        return user;
    }



}
