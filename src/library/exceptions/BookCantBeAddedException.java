package library.exceptions;

/**
 * Created by Spmart on 18.11.2016.
 */
public class BookCantBeAddedException extends Exception{
    public BookCantBeAddedException(String message) {
        super(message);
    }
}