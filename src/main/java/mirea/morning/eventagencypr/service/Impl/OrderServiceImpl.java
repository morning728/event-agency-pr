package mirea.morning.eventagencypr.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.Exception.OrderNotFoundException;
import mirea.morning.eventagencypr.model.Order;
import mirea.morning.eventagencypr.repository.OrderRepository;
import mirea.morning.eventagencypr.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public Order findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        log.info(
                result.isPresent() ?
                        "OrderService.findById() - order with id " + id + " was found" :
                        "OrderService.findById() - order with id " + id + " was not found"
        );
        if (result.isEmpty()) throw new OrderNotFoundException("OrderService.findById() - " +
                "order with id " + id + " was not found");
        return result.get();
    }

    @Override
    public Order addOrder(Order order) {
        Order result = orderRepository.save(order);
        log.info("OrderService.addOrder() - Order with id {} was added", result.getId());
        return result;
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = orderRepository.findAll();
        log.info("OrderService.findAll() - {} orders found", result.size());
        return result;
    }
}
