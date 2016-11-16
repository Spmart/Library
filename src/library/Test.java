package library;

public class Test {

    public static void main(String[] args) {
        BooksCatalogue catalogue = new BooksCatalogue();
        catalogue.readFromFile();
        catalogue.addBook("Айзек Азимов", "Конец вечности", 1999, 450);
        catalogue.addBook("А и Б Стругацкие", "Пикник на обочине", 1980, 250);
        catalogue.addBook("Роберт Хайнлайн", "Дверь в лето", 1988, 350);

        System.out.println(catalogue.getBooksQuantity());

        System.out.println(catalogue.removeBook(1));
        System.out.println(catalogue.getBooksQuantity());
        catalogue.writeToFile();
    }
}
