package mirea.morning.eventagencypr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;


@Entity
@Table(name = "Orders", schema = "mirea")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Order1 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "creation_date", nullable = true)
    private String creationDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Item> items;

    public Order1(String test4) {
        creationDate = test4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order1 order = (Order1) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return format("id: %s, creation_date: %s", id, creationDate);
    }
}
