package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.UserService;

import javax.ejb.Stateless;

@Stateless
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {
}
