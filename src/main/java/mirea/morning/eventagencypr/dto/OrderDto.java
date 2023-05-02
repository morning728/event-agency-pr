package mirea.morning.eventagencypr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.Order;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    private String name;
    private Long authorId;
    private String details;
    private Long wantedEventId;
    private Date wantedDate;

    public static OrderDto fromOrder(Order order){
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setName(order.getName());
        dto.setDetails(order.getDetails());
        dto.setAuthorId(order.getAuthorId());
        dto.setWantedDate(order.getWantedDate());
        dto.setWantedEventId(order.getWantedEventId());

        return dto;
    }

    public Order toOrder(){
        Order result = new Order();
        result.setId(id);
        result.setName(name);
        result.setDetails(details);
        result.setAuthorId(authorId);
        result.setWantedDate(wantedDate);
        result.setWantedEventId(wantedEventId);

        return result;
    }
}
