package library.view;

import library.controller.Controller;
import java.util.Scanner;

/**
 * Created by Spmart on 19.11.2016.
 */
class AppMenu {
    private Controller controller = new Controller();
    private MenuTexts texts = new MenuTexts();
    private Scanner scanner = new Scanner(System.in);

    public AppMenu() {
        printMainMenu();
        parseUsersRequest(scanner.nextLine());
    }

    private void parseUsersRequest(String request) {
        String currentRequest = request;
        switch (currentRequest) {
            case "1":
                printBooksCatalogueMenu();
                currentRequest = scanner.nextLine();
                switch (currentRequest) {
                    case "1":
                        System.out.println(texts.FIND_BOOK_MENU_DESCRIPTION);
                    case "2":
                }
            case "2":
                printCopiesCatalogueMenu();
                currentRequest = scanner.nextLine();
        }
    }

    private void printMainMenu() {
        printHead();
        System.out.println(texts.MAIN_MENU_DESCRIPTION);
        System.out.println(texts.MAIN_MENU_BOOKS_CATALOGUE);
        System.out.println(texts.MAIN_MENU_COPIES_CATALOGUE);
        System.out.println(texts.MAIN_MENU_SAVE);
        System.out.println(texts.MAIN_MENU_LOAD);
    }

    private void printHead() {
        clearScreen();
        System.out.println(texts.BORDER);
        System.out.println(texts.HEAD);
        System.out.println(texts.BORDER);
    }

    private void printBooksCatalogueMenu() {
        printHead();
        System.out.println(texts.BOOKS_CATALOGUE_DESCRIPTION);
        System.out.println(texts.BOOKS_CATALOGUE_FIND_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_ADD_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_REMOVE_BOOK);
        System.out.println(texts.BOOKS_CATALOGUE_CREATE_CATALOGUE);
        System.out.println(texts.EXIT_TO_MAIN_MENU);
    }

    private void printCopiesCatalogueMenu() {
        printHead();
        System.out.println(texts.COPIES_CATALOGUE_DESCRIPTION);
        System.out.println(texts.COPIES_CATALOGUE_FIND_COPY);
        System.out.println(texts.COPIES_CATALOGUE_FIND_ALL_COPIES);
        System.out.println(texts.COPIES_CATALOGUE_ADD_COPY);
        System.out.println(texts.COPIES_CATALOGUE_REMOVE_COPY);
        System.out.println(texts.COPIES_CATALOGUE_CREATE_CATALOGUE);
        System.out.println(texts.EXIT_TO_MAIN_MENU);
    }

    private void clearScreen() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }
}