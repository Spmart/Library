package library.controller;
import library.model.Book;
import library.model.ExampleBook;

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
        for (ExampleBook exampleBook:copiesCatalogue
             ) {
            if(exampleBook.getIdBook()==id)
            {
                copiesCatalogue.removeExampleBookByInventoryNumber(exampleBook.getInventoryNumber());
            }
        }
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
        copiesCatalogue.read();
    }

    public void writeToFile() throws IOException {
        booksCatalogue.writeToFile();
        copiesCatalogue.save();
    }

    /**
     * Метод получения всех экзепляров
     * @return массив экзепляров
     */
    public ArrayList<ExampleBook> getExamplesBooks() {
        return copiesCatalogue.getExamplesBooks();
    }

    /**
     * Метод для получения экземпляра по его инвентарному номеру
     * @param inventoryNumber инвентраный номер книги
     * @return экзепляр книги
     */
    public ExampleBook getExampleBook(int inventoryNumber)
    {
        return copiesCatalogue.getExampleBookByInventoryNumber(inventoryNumber);
    }

    /**
     * Метод установки параметра выдачи экземпляра
     * @param inventoryNumber инвентарный номер экзепляра
     * @param issue устанавливаемый параметр
     */
    public void setIssueExampleBook(int inventoryNumber, boolean issue)
    {
        copiesCatalogue.getExampleBookByInventoryNumber(inventoryNumber).setIssued(issue);
    }

    /**
     * Добавить новый экзепляр книги
     * @param authors автор книги
     * @param name название книги
     * @param publishingYear год публикации
     * @param issued выдана/ не выдана
     */
    public void addExampleBook(String authors, String name, int publishingYear,boolean issued)
    {
        copiesCatalogue.addExampleBook(booksCatalogue.getBook(authors,name,publishingYear).getId(),issued);
    }

    /**
     *
     * Добавить новый экзепляр книги
     * @param authors автор книги
     * @param name название книги
     * @param publishingYear год публикации
     * @param issued выдана/ не выдана
     * @param inventoryNumber инвентаный номер экземпляра
     */
    public void addExampleBook(String authors, String name, int publishingYear,boolean issued,int inventoryNumber)
    {
        copiesCatalogue.addExampleBook(booksCatalogue.getBook(authors,name,publishingYear).getId(),issued,inventoryNumber);
    }

    /**
     * Удалить экзепляр книги по ее инвентарному номеру
     * @param inventoryNumber инвентарынй номер
     */
    public void removeExampleBook(int inventoryNumber)
    {
        copiesCatalogue.removeExampleBookByInventoryNumber(inventoryNumber);
    }


}
