package mirea.morning.eventagencypr.controller;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.Item;
import mirea.morning.eventagencypr.model.Order1;
import mirea.morning.eventagencypr.repository.repositoryPostgres;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController1 {

    private final repositoryPostgres repository;

    private final SessionFactory sessionFactory;

    private Session session;


    @PostConstruct
    public void sessionCreation(){
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void sessionDestroying(){
        session.close();
    }

    @GetMapping("/test")
    public String temproraty() {
        return "test";
    }

    @GetMapping("/h")
    public String webhook() throws IOException, InterruptedException {
        //String str = getWebHook("asdf");
//        log.info(str);
//        if (str.contains("public_url\":\"https")) {
//            log.info(str.substring(str.indexOf("public_url\":\"https") + 13,
//                    str.indexOf("\",", str.indexOf("public_url\":\"https") + 13)));
//        }
        //log.info(getWebHook("asd"));
        return "qwe";
    }

    @GetMapping("/home")
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public String showOrders(Model model){
        //Transaction transaction = session.beginTransaction();
        List<Order1> orders = session.createQuery("SELECT o FROM Order1 o", Order1.class).getResultList();
        //transaction.commit();


        //List<Order> orders = repository.findAll();


        log.info("showOrders: All Orders were found");


        model.addAttribute("orders", orders);
        model.addAttribute("order", new Order1());
        return "home";
    }
    @PostMapping("/home")
    //@org.springframework.transaction.annotation.Transactional
    //@Transactional
    public String addOrder(@ModelAttribute("order") Order1 order1, Model model){
        //Session s = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        //session.persist(order);
        //transaction.commit();
        //s.close();






        System.out.println(order1);
        repository.saveAndFlush(order1);


        log.info("Object with id: {} was saved", order1.getId() );


        return "redirect:/home";
    }
    @DeleteMapping("/home")
    @org.springframework.transaction.annotation.Transactional
    public String deleteStation(@RequestParam("id") Long id){
        //Transaction transaction = session.beginTransaction();
//        List<Order> order = session.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class)
//                .setParameter("id", id)
//                .getResultList();
//        session.remove(order.get(0));
//        session.flush();
        //transaction.commit();


        if(repository.findById(Long.valueOf(id)).isPresent()){
            repository.delete(repository.findById(Long.valueOf(id)).get());

        }

        log.info("Object with id: {} was deleted", id );
        return "redirect:/home";
    }

    @GetMapping("/home/order")
    public String showOrders(@RequestParam("id") Long id, Model model){
        Transaction transaction = session.beginTransaction();
        Order1 order = session.createQuery("SELECT o FROM Order1 o WHERE o.id = :id", Order1.class)
                .setParameter("id", id)
                .getSingleResult();
        transaction.commit();
//        List<Order> orders = repository.findAll();
        model.addAttribute("order_items", order.getItems());
        model.addAttribute("order", order);
        model.addAttribute("item", new Item());
        return "order";
    }

}
