package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.dao.UserDAO;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {
    @Inject
    private UserDAO userDAO;

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
