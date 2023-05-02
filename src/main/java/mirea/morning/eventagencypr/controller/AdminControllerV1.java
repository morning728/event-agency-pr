package mirea.morning.eventagencypr.controller;

import lombok.RequiredArgsConstructor;
import mirea.morning.eventagencypr.dto.AdminUserDto;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.security.jwt.JwtTokenProvider;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin/")
@RequiredArgsConstructor
public class AdminControllerV1 {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
