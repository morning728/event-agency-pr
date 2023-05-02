package mirea.morning.eventagencypr.model;

import jakarta.persistence.*;
import lombok.Data;
import mirea.morning.eventagencypr.model.enums.EventType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order", schema = "mirea")
@Data
public class Order extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "details")
    private String details;

    @Column(name = "wanted_event_id")
    private Long wantedEventId;

    @Column(name = "wanted_date")
    private Date wantedDate;

    @Override
    public String toString() {
        return "Event{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }
}
