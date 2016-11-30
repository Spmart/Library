package library.model;

import library.model.Book;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by Spmart on 14.11.2016.
 */
public class BooksCatalogue {
    private static final String FILE_NAME = "books.bin";
    private ArrayList<Book> books = new ArrayList<>();
    private static int bookId;

    /**
     * Конструктор класса каталога книг.
     */
    BooksCatalogue() {
    }

    /**
     * Добавляет новую книгу.
     *
     * @param authors        Автор(ы), написавший данную книгу.
     * @param name           Название книги.
     * @param publishingYear Год издания.
     * @param pagesQuantity  Количество страниц в книге.
     * @return true, если книга была добавлена, или false, если добавления не произошло.
     */
    public void addBook(String authors, String name, int publishingYear, int pagesQuantity) {
        Book book = new Book(bookId++, authors, name, publishingYear, pagesQuantity);
        if (isCanBeAdded(authors, name, publishingYear, pagesQuantity))
            books.add(book);
        else throw new IllegalArgumentException ("Ошибка! Книнга уже существует или введены некорректные сведения.");
    }

    /**
     * Удаляет книгу по ссылке на нее.
     *
     * @param id ID книги, которую хочет удалить пользователь.
     * @return true, если книга была удалена, или false, если такую книгу найти не удалось.
     */
    public boolean removeBook(int id) {
        Book book = getBook(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        else return false;
    }

    /**
     * Считает количество книг.
     *
     * @return количество книг.
     */
    public int getBooksQuantity() {
        if (books == null) return 0;
        else return books.size();
    }

    /**
     * Возвращает все книги.
     *
     * @return все книги.
     */
    public ArrayList<Book> getBooks() {
        if (books == null) throw new NullPointerException("Ошибка! Еще не было добавлено ни одной книги.");
        return books;
    }

    /**
     * Возвращает книгу по ее ID
     *
     * @param id Идентификационный номер книги
     * @return найденную книгу или null, если книга не найдена
     */
    public Book getBook(int id) {
        for (Book book : books)
            if (book.getId() == id)
                return book;
        throw new IllegalArgumentException("Ошибка! Книги с таким ID не существует."); //Исключение вывалится, если книга не будет найдена
    }

    /**
     * Возвращает книгу по указанным параметрам.
     *
     * @param authors        Автор(ы), написавший данную книгу.
     * @param name           Название книги.
     * @param publishingYear Год издания.
     * @return Null, если книга не найдена или найденную книгу.
     */
    public Book getBook(String authors, String name, int publishingYear) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            String bookAuthors = book.getAuthors();
            String bookName = book.getName();
            int bookPublishingYear = book.getPublishingYear();
            if (bookAuthors.equalsIgnoreCase(authors) && bookName.equalsIgnoreCase(name)
                    && bookPublishingYear == publishingYear) return book;
        }
        throw new IllegalArgumentException("Ошибка! Искомой книги не существует. Проверьте введеные данные.");
    }

    /**
     * Читает каталог из файла.
     */
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        books = (ArrayList<Book>) (objectInputStream.readObject());
        objectInputStream.close();
    }

    /**
     * Записывает каталог в файл.
     */
    public void writeToFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(books);
        objectOutputStream.close();
    }

    /**
     * Проверяет, существует ли книга с введенными данными.
     * Использует метод getBook. Если появляется исключение, то ловим его и возвращаем false.
     *
     * @return true, если книга уже существует в каталоге, или false, если такой книги в каталоге еще нет.
     */
    private boolean isExist(String authors, String name, int publishingYear) {
        try {
            getBook(authors, name, publishingYear);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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

    /**
     * Проверяет возможность добавления книги в каталог.
     *
     * @param authors        Автор(ы), написавший данную книгу.
     * @param name           Название книги.
     * @param publishingYear Год издания.
     * @param pagesQuantity  Количество страниц в книге.
     * @return True, если книгу добавить возможно. False, если невозможно.
     */
    private boolean isCanBeAdded(String authors, String name, int publishingYear, int pagesQuantity) {
        if (!isCorrectYear(publishingYear) || pagesQuantity < 0 || isExist(authors, name, publishingYear))
            return false;
        else return true;
    }
}