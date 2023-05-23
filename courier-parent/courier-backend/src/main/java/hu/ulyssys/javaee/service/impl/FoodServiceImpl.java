package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.service.FoodService;

import javax.ejb.Stateless;

@Stateless
public class FoodServiceImpl extends CoreServiceImpl<Food> implements FoodService {
}
