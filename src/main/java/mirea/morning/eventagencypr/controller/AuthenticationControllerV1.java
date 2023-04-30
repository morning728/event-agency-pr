package mirea.morning.eventagencypr.controller;

import mirea.morning.eventagencypr.dto.AuthenticationRequestDto;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.security.jwt.JwtTokenProvider;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestBody AuthenticationRequestDto requestDto, Model model) {
        try {
            String username = requestDto.getUsername();
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            } catch (AuthenticationException e){
                model.addAttribute("errorMsg", "Invalid");

            }
            User user = userService.findByUsername(username);

            if (user == null) {
                model.addAttribute("errorMsg", "Invalid");
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            System.out.println(user.getRoles());
            String token = jwtTokenProvider.createToken(username, user.getRoles());

//            Map<Object, Object> response = new HashMap<>();
//            response.put("username", username);
//            response.put("token", token);

            model.addAttribute("username", username);
            model.addAttribute("token", token);

            return "successfulAuth";
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
