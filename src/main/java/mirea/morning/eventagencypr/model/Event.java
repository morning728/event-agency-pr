package mirea.morning.eventagencypr.model;

import jakarta.persistence.*;
import lombok.Data;
import mirea.morning.eventagencypr.model.enums.EventType;

import java.util.List;

@Entity
@Table(name = "event", schema = "mirea")
@Data
public class Event extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "price_for_person")
    private Integer priceForPerson;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "minimum_price")
    private Long minimumPrice;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "basket", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Event{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";

    }
}
