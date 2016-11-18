package library.exceptions;

/**
 * Created by Spmart on 18.11.2016.
 */
public class BookNotExistException extends Exception {
    public BookNotExistException(String message) {
        super(message);
    }
}
