package mirea.morning.eventagencypr.repository;
import mirea.morning.eventagencypr.model.Item;
import mirea.morning.eventagencypr.model.Order1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@Service
//@Data
public class Repository {

    private ArrayList<Order1> orderList = new ArrayList<Order1>(Arrays.asList(
            new Order1("test4"),
            new Order1("test5"),
            new Order1("test6")
    ));
    private List<Item> itemList = new ArrayList<Item>(Arrays.asList(
//            new Item("test1", "test11"),
//            new Item("test2", "test22"),
//            new Item("test3", "test33")
    ));

    public void addOrderByCreationDate(String date){
        orderList.add(new Order1(date));
    }

    public void removeOrderByCreationDate(String date){
        orderList.remove(orderList.stream().filter(u -> Objects.equals(u.getCreationDate(), date)).findAny().orElse(null));
    }
    public void addItemByCreationDate(String date){
//        itemList.add(new Item(date, "40"));
    }

    public void removeItemByCreationDate(String date){
        if(itemList.stream().anyMatch(u -> Objects.equals(u.getCreateDate(), date))){
//            itemList.remove(new Item(date, "40"));
        }
    }




}

