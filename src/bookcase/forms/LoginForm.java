package bookcase.forms;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by Student on 12-4-2016.
 */
public class LoginForm {



    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private String url;







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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
