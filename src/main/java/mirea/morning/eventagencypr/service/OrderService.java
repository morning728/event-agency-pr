package mirea.morning.eventagencypr.service;

import mirea.morning.eventagencypr.model.Order;

import java.util.List;

public interface OrderService {

    Order findById(Long id);

    Order addOrder(Order order);

    List<Order> findAll();
}
