package library;
import java.util.ArrayList;
import java.util.Iterator;

public class BooksCatalogue {
    ArrayList<Book> books = new ArrayList<>();

    /**
     * Конструктор класса каталога книг. Принимает книгу.
     * @param book Книга, которую хочет добавить пользователь в новый каталог
     */
    public BooksCatalogue(Book book) {
        books.add(book);
    }

    /**
     * Конструктор класса каталога книг. Принимает массив книг.
     * @param books Массив книг, которые хочет добавить полтьзователь.
     */
    public BooksCatalogue(Book[] books) {
        for (Book book : books) addBook(book);
    }

    /**
     * Конструктор класса каталога книг.
     */
    public BooksCatalogue() {}

    /**
     * Добавляет новую книгу.
     * @param book Книга, которую хочет добавить пользователь.
     * @return true, если книга была добавлена, или false, если добавления не произошло.
     */
    public boolean addBook(Book book) {
        if (isExist(book)) return false;
        else {
            books.add(book);
            return true;
        }
    }

    /**
     * Удаляет книгу по ссылке на нее.
     * @param book Книга, которую хочет удалить пользователь.
     * @return true, если книга была удалена, или false, если такую книгу найти не удалось.
     */
    public boolean removeBook(Book book) {
        if (isExist(book)) {
            books.remove(book);
            return true;
        }
        else return false;
    }

    /**
     * Считает количество книг.
     * @return количество книг.
     */
    public int countBooks() {
        return books.size();
    }

    /**
     * Возвращает все книги.
     * @return все книги.
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     *
     * @param book Книга, которую нужно проверить на уникальность.
     * @return true, если книга уже существует в каталоге, или false, если такой книги в каталоге еще нет.
     */
    private boolean isExist(Book book) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext())
            if (iterator.next().equals(book)) return true;
        return false;
    }
}
