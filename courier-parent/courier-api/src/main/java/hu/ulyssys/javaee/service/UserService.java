package hu.ulyssys.javaee.service;

import hu.ulyssys.javaee.entity.User;

public interface UserService extends CoreService<User> {
    User findByUsername(String username);
}
