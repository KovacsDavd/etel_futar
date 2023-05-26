package hu.ulyssys.javaee.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.username=:username")
@Entity
@Table(name = "app_user")
public class User extends CoreEntity {
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    @Column(name = "username", nullable = false, length = 200, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
