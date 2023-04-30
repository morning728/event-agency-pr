package mirea.morning.eventagencypr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users",  schema = "mirea")
@Data
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},//ИЗУЧИТЬ
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_basket",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},//ИЗУЧИТЬ
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "id")})
    private List<Event> basket;
}
