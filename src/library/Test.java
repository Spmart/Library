package library;

public class Test {

    public static void main(String[] args) {
        Book firstBook = new Book("1", "Айзек Азимов", "Конец вечности", 1999, 450);
        Book secondBook = new Book("2", "А и Б Стругацкие", "Пикник на обочине", 1980, 250);
        Book thirdBook = new Book("3", "Роберт Хайнлайн", "Дверь в лето", 1988, 350);
	    BooksCatalogue catalogue = new BooksCatalogue(firstBook);
        System.out.println(catalogue.addBook(firstBook));
        System.out.println(catalogue.addBook(secondBook));
        System.out.println(catalogue.addBook(thirdBook));
        System.out.println(catalogue.countBooks());

        System.out.println(catalogue.removeBook(secondBook));
        System.out.println(catalogue.countBooks());
    }
}
