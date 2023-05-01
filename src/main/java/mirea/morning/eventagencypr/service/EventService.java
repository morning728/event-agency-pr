package mirea.morning.eventagencypr.service;

import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.User;
import mirea.morning.eventagencypr.model.enums.EventType;

import java.util.List;

public interface EventService {

    List<Event> getAll();

    Event findByName(String name);

    List<Event> findByType(EventType type);
    Event findById(Long id);
    List<Event> findByPriceForPerson(Long min, Long max);
    List<Event> findByMinPrice(Long min, Long max);

    List<Event> filter(
            String type,
            Long minPriceForPerson,
            Long maxPriceForPerson,
            Long minMinPrice,
            Long maxMinPrice
    );

    Event updateEvent(Event event);
}
