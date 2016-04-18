package bookcase.forms;

import javax.validation.constraints.Size;

/**
 * Created by Student on 15-4-2016.
 */


public class SignupForm {
    @Size(min=4, max=20, message="blaat")
    private String username;
    @Size(min=8, max=20)
    private String password;
    @Size(min=8, max=20)
    private String password2;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
