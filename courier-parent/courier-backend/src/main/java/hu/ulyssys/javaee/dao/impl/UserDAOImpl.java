package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.UserDAO;
import hu.ulyssys.javaee.entity.User;

import javax.ejb.Stateless;
@Stateless
public class UserDAOImpl extends CoreDAOImpl<User> implements UserDAO {
    @Override
    protected Class<User> getManagedClass() {
        return User.class;
    }

}
