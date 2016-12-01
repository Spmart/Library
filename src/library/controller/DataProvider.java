package library.controller;
import library.Observer.Observable;
import library.Observer.Observer;
import library.controller.Journal.Journal;
import library.controller.Journal.Note;
import library.model.Book;
import library.model.ExampleBook;
import library.model.InventoryNumberGenerator;

import java.io.IOException;
import java.util.ArrayList;


public class DataProvider implements Observable{
    private BooksCatalogue booksCatalogue; //Контроллер книг
    private CopiesCatalogue copiesCatalogue; //Контроллер экземпляров книг

    private Journal journal;//Журнал изменений

    private ArrayList<Observer> observers;//Массив слушателей

    public DataProvider() {
        booksCatalogue = new BooksCatalogue();
        copiesCatalogue = new CopiesCatalogue();
        observers=new ArrayList<Observer>();
        journal=new Journal();
    }

    public void addBook(String authors, String name, int publishingYear, int pagesQuantity) {
        booksCatalogue.addBook(authors, name, publishingYear, pagesQuantity);
        journal.AddNotes(Journal.ADDED_BOOK);
        notifyListeners();
    }
    public void setBook(int idBook,Book book)
    {
        booksCatalogue.setBook(idBook,book);
        journal.AddNotes(Journal.UPDATED_BOOK);
        notifyListeners();

    }

    /**
     * Каскадное удаление книги
     * @param id удалямой книги
     */
    public void removeBook(int id) {
        booksCatalogue.removeBook(id);
        int length=copiesCatalogue.length();
        for(int i=0;i<length;i++)
        {
            if(copiesCatalogue.getExampleBookByIndex(i).getIdBook()==id)
            {
               copiesCatalogue.removeExampleBookByIndex(i);
                length--;
                i--;
            }
        }
        journal.AddNotes(Journal.REMOVED_BOOK);
        notifyListeners();
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
    public Book getBook(int id)
    {
        return booksCatalogue.getBook(id);
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
        journal.AddNotes(Journal.UPDATED_EXAMPLE_BOOK);
        notifyListeners();
    }

    /**
     * Метод изменения экзепляра по инвентарному номеру( с изменением его)
     * @param inventoryNumber инвентарный номер изменяемой книги
     * @param exampleBook новая книга
     */
    public void setExampleBook(int inventoryNumber, ExampleBook exampleBook)
    {
        copiesCatalogue.setExamplesBooksByInventoryNumber(inventoryNumber,exampleBook);
        journal.AddNotes(Journal.UPDATED_EXAMPLE_BOOK);
        notifyListeners();
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
        journal.AddNotes(Journal.ADDED_EXAMPLE_BOOK);
        notifyListeners();
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
        journal.AddNotes(Journal.ADDED_EXAMPLE_BOOK);
        notifyListeners();
    }

    /**
     * Удалить экзепляр книги по ее инвентарному номеру
     * @param inventoryNumber инвентарынй номер
     */
    public void removeExampleBook(int inventoryNumber)
    {
        copiesCatalogue.removeExampleBookByInventoryNumber(inventoryNumber);
        journal.AddNotes(Journal.REMOVED_EXAMPLE_BOOK);
        notifyListeners();
    }

    /**
     * Метод выдает последнее изменение
     * @return Note - запись последнего изменения
     */
    public Note getLastNotes()
    {
        return journal.getLastNote();
    }

    /**
     * Метод оповещения слушателей
     */
    @Override
    public void notifyListeners() {
        for(Observer observer: observers) {
            observer.listenEvent();
        }
    }

    /**
     * Метод добавления слушателя
     * @param observer добавляемый слушатель
     */
    @Override
    public void addListener(Observer observer) {
        observers.add(observer);
    }

    /**
     * Метод удаления слушателя
     * @param observer удаляемый слушатель
     */
    @Override
    public void deleteListener(Observer observer) {
        observers.remove(observer);
    }

}
