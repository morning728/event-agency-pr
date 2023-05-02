package mirea.morning.eventagencypr.controller;

import lombok.RequiredArgsConstructor;
import mirea.morning.eventagencypr.dto.UserDto;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.security.jwt.JwtTokenProvider;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users/")
@RequiredArgsConstructor
public class UserRestControllerV1 {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //System.out.println(user.getRoles().toString());
        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "getUsername")
    public ResponseEntity getUserById(@RequestParam(name = "token", required = true) String token) {
        return new ResponseEntity<>(userService.findByUsername(jwtTokenProvider.getUsername(token)), HttpStatus.OK);
    }
}
