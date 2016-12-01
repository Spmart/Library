package library.model;

import java.text.SimpleDateFormat;

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
        setAuthors(authors);
        setName(name);
        setPublishingYear(publishingYear);
        setPagesQuantity(pagesQuantity);
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
        if(authors.isEmpty())
            throw new IllegalArgumentException ("Имя автора не может отсутствовать");
        this.authors = authors;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name.isEmpty())
            throw new IllegalArgumentException ("Название книги не может отсутствовать");
        this.name = name;

    }

    public int getPublishingYear() {
        return publishingYear;
    }
    public void setPublishingYear(int publishingYear) {
        if(!isCorrectYear(publishingYear))
            throw new IllegalArgumentException ("Введен неверный год");
        this.publishingYear = publishingYear;
    }

    public int getPagesQuantity() {
        return pagesQuantity;
    }
    public void setPagesQuantity(int pagesQuantity) {
        if(pagesQuantity<1)
            throw new IllegalArgumentException("Неверное количество страниц");
        this.pagesQuantity = pagesQuantity;

    }
    /**
     * Проверяет введенный год на корректность.
     *
     * @param year Год.
     * @return True, если год корректен. False, если год некорректен.
     */
    private boolean isCorrectYear(int year) {
        long currentTimeMillis = System.currentTimeMillis();
        String currentYear = new SimpleDateFormat("yyyy").format(currentTimeMillis);
        if (year < 100 || year > Integer.valueOf(currentYear)) return false;
        else return true;
    }
}