package mirea.morning.eventagencypr.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.enums.EventType;
import mirea.morning.eventagencypr.repository.EventRepository;
import mirea.morning.eventagencypr.service.EventService;
import mirea.morning.eventagencypr.service.UserService;
import org.springframework.stereotype.Service;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final UserService userService;
    private final EventRepository repository;

    @Override
    public List<Event> getAll() {
        log.info("EventRepository.getAll() - All events were found");
        return repository.findAll();
    }

    @Override
    public Event findByName(String name) {
        Event result = repository.findByName(name);
        if(result == null){
            log.info("EventRepository.findByName() - Event with name {} was not found", name);
            return null;
        }
        log.info("EventRepository.findByName() - Event with name {} was found", name);
        return result;
    }

    @Override
    public List<Event> findByType(EventType type) {
        List<Event> result = repository.findByType(type);
        if(result.isEmpty()){
            log.info("EventRepository.findByType() - no one event with type {} was found", type.toString());
            return null;
        }
        log.info("EventRepository.findByType() - all events with type {} were found", type.toString());
        return result;
    }

    @Override
    public List<Event> findByPriceForPerson(Long min, Long max) {
        List<Event> result;
        max = max == null ? 0 : max;
        min = min == null ? 0 : min;

        result = repository.findByPriceForPerson(min, max);

        if(result.isEmpty()){
            log.info("EventRepository.findByPriceForPerson() - no one event with price for person between {} and {} was found", min, max);
        } else {
            log.info("EventRepository.findByPriceForPerson() - all events with price for person between {} and {} were found", min, max);
        }
        return result;
    }

    @Override
    public List<Event> findByMinPrice(Long min, Long max) {
        List<Event> result;
        max = max == null ? 0 : max;
        min = min == null ? 0 : min;

        result = repository.findByMinPrice(min, max);

        if(result.isEmpty()){
            log.info("EventRepository.findByMinPrice() - no one event with min price between {} and {} was found", min, max);
        } else {
            log.info("EventRepository.findByMinPrice() - all events with min price between {} and {} were found", min, max);
        }
        return result;
    }

    @Override
    public List<Event> filter(EventType type,
                              Long minPriceForPerson,
                              Long maxPriceForPerson,
                              Long minMinPrice,
                              Long maxMinPrice) {
        if (type != null)
            return findByType(type);
        else if (maxMinPrice != null || minMinPrice != null)
            return findByMinPrice(minMinPrice, maxMinPrice);
        else if (minPriceForPerson != null || maxPriceForPerson != null)
            return findByPriceForPerson(minPriceForPerson, maxPriceForPerson);
        else
            return getAll();
    }
}
