package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.UserDAO;
import hu.ulyssys.javaee.entity.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDAOImpl extends CoreDAOImpl<User> implements UserDAO {

    @Override
    protected Class<User> getManagedClass() {
        return User.class;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = getEntityManager().createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }
}
