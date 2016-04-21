package bookcase.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Student on 21-4-2016.
 */
public class AddCaseForm {
    @Size(min=4, max=20, message="Name size must be between 4-20 characters")
    private String name;

    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
