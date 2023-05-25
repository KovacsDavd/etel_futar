package hu.ulyssys.javaee.service;

import hu.ulyssys.javaee.entity.User;

import javax.inject.Inject;

public interface UserService extends CoreService<User> {
    User findByUsername(String username);
}
