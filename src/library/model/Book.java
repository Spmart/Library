package library.model;

/**
 * Created by Spmart on 14.11.2016.
 */
public class Book implements java.io.Serializable {
    private int id;
    private String authors;
    private String name;
    private int publishingYear;
    private int pagesQuantity;

    /**
     * Конструктор класса Book
     * @param id Идентификатор книги
     * @param authors Автор(ы), написавший данную книгу
     * @param name Название книги
     * @param publishingYear Год издания
     * @param pagesQuantity Количество страниц в книге
     */


    public Book(int id, String authors, String name, int publishingYear, int pagesQuantity) {
        this.id = id;
        this.authors = authors;
        this.name = name;
        this.publishingYear = publishingYear;
        this.pagesQuantity = pagesQuantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPublishingYear() {
        return publishingYear;
    }
    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public int getPagesQuantity() {
        return pagesQuantity;
    }
    public void setPagesQuantity(int pagesQuantity) {
        this.pagesQuantity = pagesQuantity;
    }
}