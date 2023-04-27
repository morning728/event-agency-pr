package mirea.morning.eventagencypr.rest;

import mirea.morning.eventagencypr.dto.UserDto;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //System.out.println(user.getRoles().toString());
        UserDto result = UserDto.fromUser(user);


        User u = new User();
        u.setUsername("mama");
        u.setPassword("papa");
        u.setFirstName("papa");
        u.setLastName("papa");
        u.setEmail("papa");
        u.setCreated(new Date());
        u.setUpdated(new Date());

        userService.register(u);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
