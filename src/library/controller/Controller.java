package library.controller;

import library.model.DataProvider;
import library.view.AppMenuPrinter;
import java.util.Scanner;

/**
 * Created by Spmart on 30.11.2016.
 */
public class Controller {
    private AppMenuPrinter printer = new AppMenuPrinter();
    private DataProvider dataProvider = new DataProvider();
    private Scanner scanner = new Scanner(System.in);

    public Controller() throws java.lang.ClassNotFoundException, java.io.IOException {
        printer.printMainMenu();
        parseUsersRequest(scanner.nextLine());
        //dataProvider.addObserver(this); TODO: Выяснить, почему это не работает
    }

    public void handleBookEvent() {
        dataProvider.getJournal();
    }

    /**
     * Обрабатывает введенные пользователем данные
     * @param request введенный пользователем пункт меню
     */
    private void parseUsersRequest(String request) { //TODO: В контроллер
        String currentRequest = request;
        switch (currentRequest) {
            case "1":
                printer.printBooksCatalogueMenu();
                currentRequest = scanner.nextLine();
                switch (currentRequest) {
                    case "1":

                    case "2":
                }
            case "2":
                printer.printCopiesCatalogueMenu();
                currentRequest = scanner.nextLine();
        }
    }
}