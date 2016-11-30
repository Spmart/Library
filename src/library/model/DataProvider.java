package library.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Spmart on 18.11.2016.
 */
public class DataProvider {
    private ArrayList<BookEventObserver> observers;
    private String message;
    private BooksCatalogue booksCatalogue; //Контроллер книг
    private CopiesCatalogue copiesCatalogue; //Контроллер экземпляров книг

    public DataProvider() throws java.lang.ClassNotFoundException, java.io.IOException {
        booksCatalogue = new BooksCatalogue();
        copiesCatalogue = new CopiesCatalogue();
    }

    public void addObserver(BookEventObserver observer) {
        observers.add(observer);
    }

    public String getJournal() {
        return message;
    }

    public void addBook(String authors, String name, int publishingYear, int pagesQuantity) {
        booksCatalogue.addBook(authors, name, publishingYear, pagesQuantity);
        message = "Добавлена книга";
        for (BookEventObserver o : observers ){
            o.handleBookEvent();
        }
    }

    public void removeBook(int id) {
        booksCatalogue.removeBook(id);
    }

    public int getBooksQuantity() {
        return booksCatalogue.getBooksQuantity();
    }

    public ArrayList<Book> getBooks() {
        return booksCatalogue.getBooks();
    }

    public Book getBook(String authors, String name, int publishingYear) {
        return booksCatalogue.getBook(authors, name, publishingYear);
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        booksCatalogue.readFromFile();
    }

    public void writeToFile() throws IOException {
        booksCatalogue.writeToFile();
    }
}
