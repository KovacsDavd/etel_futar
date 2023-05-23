package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.FoodDAO;
import hu.ulyssys.javaee.entity.Food;

import javax.ejb.Stateless;

@Stateless
public class FoodDAOImpl extends CoreDAOImpl<Food> implements FoodDAO {
    @Override
    protected Class<Food> getManagedClass() {
        return Food.class;
    }
}
