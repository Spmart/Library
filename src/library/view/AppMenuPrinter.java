package library.view;

/**
 * Created by Spmart on 19.11.2016.
 */
public class AppMenuPrinter {
    private MenuTextsConstants texts = new MenuTextsConstants();

    /**
     * Выводит на экран главное меню приложения
     */
    public void printMainMenu() {
        printHead();
        System.out.println(texts.MAIN_MENU_DESCRIPTION);
        System.out.println(texts.MAIN_MENU_BOOKS_CATALOGUE);
        System.out.println(texts.MAIN_MENU_COPIES_CATALOGUE);
        System.out.println(texts.MAIN_MENU_SAVE);
        System.out.println(texts.MAIN_MENU_LOAD);
    }

    /**
     * Выводит на экран шапку приложения
     */
    private void printHead() {
        clearScreen();
        System.out.println(texts.BORDER);
        System.out.println(texts.HEAD);
        System.out.println(texts.BORDER);
    }

    /**
     * Выводит на экран меню каталога книг
     */
    public void printBooksCatalogueMenu() {
        printHead();
        System.out.println(texts.BOOKS_CATALOGUE_DESCRIPTION);
        System.out.println(texts.BOOKS_CATALOGUE_FIND_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_ADD_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_REMOVE_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_CREATE_CATALOGUE);
        System.out.println(texts.EXIT_TO_MAIN_MENU);
    }

    /**
     * Выводит на экран меню каталога экземпляров
     */
    public void printCopiesCatalogueMenu() {
        printHead();
        System.out.println(texts.COPIES_CATALOGUE_DESCRIPTION);
        System.out.println(texts.COPIES_CATALOGUE_FIND_COPY);
        System.out.println(texts.COPIES_CATALOGUE_FIND_ALL_COPIES);
        System.out.println(texts.COPIES_CATALOGUE_ADD_COPY);
        System.out.println(texts.COPIES_CATALOGUE_REMOVE_COPY);
        System.out.println(texts.COPIES_CATALOGUE_CREATE_CATALOGUE);
        System.out.println(texts.EXIT_TO_MAIN_MENU);
    }

    /**
     * Очищает экран
     */
    public void clearScreen() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }
}