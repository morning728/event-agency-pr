package mirea.morning.eventagencypr.model.Exception;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends NoSuchElementException {
    public OrderNotFoundException(String s) {
        super(s);
    }
}
