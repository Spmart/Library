package library;
import java.util.ArrayList;
import java.util.Iterator;

public class BooksCatalogue {
    ArrayList<Book> books = new ArrayList<>();
    private static int bookId = -1; //Чтобы начать считать с нуля

    /**
     * Конструктор класса каталога книг. Принимает книгу.
     * @param book Книга, которую хочет добавить пользователь в новый каталог
     */
    public BooksCatalogue(Book book) {
        books.add(book);
    }

    /**
     * Конструктор класса каталога книг.
     */
    public BooksCatalogue() {}

    /**
     * Добавляет новую книгу.
     * @return true, если книга была добавлена, или false, если добавления не произошло.
     */
    public boolean addBook(String authors, String name, int publishingYear, int pagesQuantity) {
        bookId++;
        Book book = new Book(bookId, authors, name, publishingYear, pagesQuantity);
        if (isExist(authors, name, publishingYear)) return false;
        else {
            books.add(book);
            return true;
        }
    }

    /**
     * Удаляет книгу по ссылке на нее.
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
     * Возвращает книгу по ее ID
     * @param id Идентификационный номер книги
     * @return найденную книгу или null, если книга не найдена
     */
    public Book getBook(int id) {
        for (Book book: books)
            if (book.getId() == id)
                return book;
        return null;
    }

    /**
     * @return true, если книга уже существует в каталоге, или false, если такой книги в каталоге еще нет.
     */
    private boolean isExist(String authors, String name, int publishingYear) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            String bookAuthors = book.getAuthors();
            String bookName = book.getName();
            int bookPublishingYear = book.getPublishingYear();
            if (bookAuthors.equalsIgnoreCase(authors) && bookName.equalsIgnoreCase(name)
                    && bookPublishingYear == publishingYear) return true;
        }
        return false;
    }
}