package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.CourierDAO;
import hu.ulyssys.javaee.entity.Courier;

import javax.ejb.Stateless;

@Stateless
public class CourierDAOImpl extends CoreDAOImpl<Courier> implements CourierDAO {
    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }
}
