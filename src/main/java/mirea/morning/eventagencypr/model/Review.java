package mirea.morning.eventagencypr.model;

import jakarta.persistence.*;
import lombok.Data;
import mirea.morning.eventagencypr.model.enums.EventType;

import java.util.List;

@Entity
@Table(name = "review", schema = "mirea")
@Data
public class Review extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "author_id")
    private Long authorId;

    @Override
    public String toString() {
        return "Event{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";

    }
}
