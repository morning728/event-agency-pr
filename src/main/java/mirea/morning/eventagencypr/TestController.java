package mirea.morning.eventagencypr;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    @Autowired
    private final test rep;

    @RequestMapping("/all")
    private Book getAll(){
        //System.out.println(rep.getAllBooks());

        rep.listCustomers();
        return new Book();
    }
}
