package app.validation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class FormUser {
    @NotEmpty(message = "Fullname field shouldn't be empty")
    private String name;

    @NotEmpty(message = "Email field shouldn't be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @Size(min = 8, message = "Password length shouldn't be less than 8")
    private String password;
}
