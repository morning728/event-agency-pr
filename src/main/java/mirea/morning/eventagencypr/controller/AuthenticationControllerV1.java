package mirea.morning.eventagencypr.controller;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.dto.AuthenticationRequestDto;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.model.enums.Status;
import mirea.morning.eventagencypr.security.jwt.JwtTokenProvider;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/v1/auth/")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("login")
    public String toLogin(Model model) {
        return "Auth/login";
    }

    @GetMapping("register")
    public String toRegister(Model model) {
        return "Auth/register";
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto, Model model) {
        String username = requestDto.getUsername();
        Map<Object, Object> response = new HashMap<>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
        } catch (AuthenticationException e){
            response.put("errorMsg", "Invalid data");
            return(ResponseEntity.ok(response));
        }

        User user = userService.findByUsername(username);

        if (user == null) {
            log.error("User with name {} was not found", requestDto.getUsername());
            response.put("errorMsg", "Invalid data");
            return(ResponseEntity.ok(response));
        }
        System.out.println(user.getRoles());
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        response.put("username", username);
        response.put("token", token);


        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    @ResponseBody
    public ResponseEntity register(@RequestBody AuthenticationRequestDto requestDto, Model model) {
        User usr = new User();
        usr.setPassword(requestDto.getPassword());
        usr.setUsername(requestDto.getUsername());
        usr.setUpdated(new Date());
        usr.setCreated(new Date());
        usr.setStatus(Status.ACTIVE);
        userService.register(usr);
        String username = requestDto.getUsername();
        Map<Object, Object> response = new HashMap<>();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
        } catch (AuthenticationException e){
            response.put("errorMsg", "Invalid data");
            return(ResponseEntity.ok(response));
        }

        User user = userService.findByUsername(username);

        if (user == null) {
            log.error("User with name {} was not found", requestDto.getUsername());
            response.put("errorMsg", "Invalid data");
            return(ResponseEntity.ok(response));
        }
        System.out.println(user.getRoles());
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        response.put("username", username);
        response.put("token", token);


        return ResponseEntity.ok(response);
    }

    @GetMapping("check")
    @ResponseBody
    public ResponseEntity checkToken() {
        return ResponseEntity.ok(200);
    }

    @GetMapping("checkAdmin")
    @ResponseBody
    public ResponseEntity checkTokenRole() {
        return ResponseEntity.ok(200);
    }
}
