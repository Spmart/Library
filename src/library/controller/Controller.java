package library.controller;
import library.model.Book;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Spmart on 18.11.2016.
 */
public class Controller {
    private BooksCatalogue booksCatalogue; //Контроллер книг
    private CopiesCatalogue copiesCatalogue; //Контроллер экземпляров книг

    public Controller()throws java.lang.ClassNotFoundException,java.io.IOException {
        booksCatalogue = new BooksCatalogue();
        copiesCatalogue = new CopiesCatalogue();
    }

    public void addBook(String authors, String name, int publishingYear, int pagesQuantity) {
        booksCatalogue.addBook(authors, name, publishingYear, pagesQuantity);
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
        copiesCatalogue.save();
    }
    public void addExampleBook(String authors, String name, int publishingYear,boolean issued)
    {
        copiesCatalogue.addExampleBook(booksCatalogue.getBook(authors,name,publishingYear).getId(),issued);
    }


}
