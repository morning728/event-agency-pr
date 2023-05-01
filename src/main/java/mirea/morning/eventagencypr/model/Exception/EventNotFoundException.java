package mirea.morning.eventagencypr.model.Exception;

import java.util.NoSuchElementException;

public class EventNotFoundException extends NoSuchElementException {
    public EventNotFoundException(String s) {
        super(s);
    }
}
