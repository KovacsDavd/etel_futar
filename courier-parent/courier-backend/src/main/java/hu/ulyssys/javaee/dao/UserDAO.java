package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.User;

public interface UserDAO extends CoreDAO<User> {
    User findByUsername(String username);
}
