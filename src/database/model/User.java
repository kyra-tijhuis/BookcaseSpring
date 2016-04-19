package database.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kyra on 13/04/2016.
 */
@Entity
public class User {
    private int userID;
    private String userName;
    private String passwordHash;
    private String salt;
    private Collection<Bookcase> bookcases;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, targetEntity = Bookcase.class)
    public Collection<Bookcase> getBookcases() {
        return bookcases;
    }

    public void setBookcases(Collection<Bookcase> bookcases) {
        this.bookcases = bookcases;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userID != user.userID) return false;
        if (!userName.equals(user.userName)) return false;
        if (!passwordHash.equals(user.passwordHash)) return false;
        return salt.equals(user.salt);

    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 31 * result + userName.hashCode();
        result = 31 * result + passwordHash.hashCode();
        result = 31 * result + salt.hashCode();
        return result;
    }
}
