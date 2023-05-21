package hu.ulyssys.javaee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class User extends CoreEntity {
    @Column(name = "user_name", nullable = false, length = 200)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password; //TODO titkosítani


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
