package mirea.morning.eventagencypr.model.Exception;

import java.util.NoSuchElementException;

public class ReviewNotFoundException extends NoSuchElementException {
    public ReviewNotFoundException(String s) {
        super(s);
    }
}
