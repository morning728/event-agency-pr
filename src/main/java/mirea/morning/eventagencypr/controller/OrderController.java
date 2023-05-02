package mirea.morning.eventagencypr.controller;


import lombok.RequiredArgsConstructor;
import mirea.morning.eventagencypr.dto.OrderDto;
import mirea.morning.eventagencypr.model.Order;
import mirea.morning.eventagencypr.service.OrderService;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;


    @GetMapping("/")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "Orders/ordersMain";
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity addOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.addOrder(orderDto.toOrder()));
    }
}
