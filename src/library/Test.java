package library;

import library.controller.BooksCatalogue;
import library.exceptions.BookCantBeAddedException;
import library.exceptions.BookNotExistException;

public class Test {

    public static void main(String[] args) throws BookNotExistException, BookCantBeAddedException {
        BooksCatalogue catalogue = new BooksCatalogue();
        //catalogue.readFromFile();
        catalogue.addBook("Айзек Азимов", "Конец вечности", 2017, 450);
        catalogue.addBook("А и Б Стругацкие", "Пикник на обочине", 1980, 250);
        catalogue.addBook("Роберт Хайнлайн", "Дверь в лето", 1988, 350);

        System.out.println(catalogue.getBooksQuantity());

        System.out.println(catalogue.removeBook(1));
        System.out.println(catalogue.getBooksQuantity());
        //catalogue.writeToFile();
    }
}
