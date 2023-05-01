package mirea.morning.eventagencypr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.model.enums.EventType;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
    private Long id;
    private String name;


    private Integer priceForPerson;



    private String type;


    private Long minimumPrice;


    private String description;

    public static EventDto fromEvent(Event event){
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setMinimumPrice(event.getMinimumPrice());
        dto.setPriceForPerson(event.getPriceForPerson());
        dto.setType(String.valueOf(event.getType()));

        return dto;
    }

}
