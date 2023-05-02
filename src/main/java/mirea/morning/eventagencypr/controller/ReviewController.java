package mirea.morning.eventagencypr.controller;

import lombok.RequiredArgsConstructor;
import mirea.morning.eventagencypr.dto.OrderDto;
import mirea.morning.eventagencypr.dto.ReviewDto;
import mirea.morning.eventagencypr.model.Review;
import mirea.morning.eventagencypr.security.jwt.JwtTokenProvider;
import mirea.morning.eventagencypr.service.OrderService;
import mirea.morning.eventagencypr.service.ReviewService;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final UserService userService;
    private final ReviewService reviewService;


    @GetMapping("/")
    public String getAllOrders(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "Reviews/reviewsMain";
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity addReview(@RequestBody ReviewDto reviewDto) {

        return ResponseEntity.ok(reviewService.addReview(reviewDto.toReview()));
    }
}
