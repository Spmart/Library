package library.view;

/**
 * Created by Spmart on 19.11.2016.
 */
class MenuTextsConstants {
    static final String BORDER = "=============================================================================";
    static final String HEAD = "=================Справочная система лучшей в мире библиотеки=================";
    static final String EXIT_TO_MAIN_MENU = "Выйти в главное меню";

    static final String MAIN_MENU_DESCRIPTION = "Выберите картотеку. Для выбора используйте цифровую клавиатуру.";
    static final String MAIN_MENU_BOOKS_CATALOGUE = "1. Картотека книг";
    static final String MAIN_MENU_COPIES_CATALOGUE = "2. Картотека экземпляров";
    static final String MAIN_MENU_SAVE = "3. Сохранить данные на диск";
    static final String MAIN_MENU_LOAD = "4. Загрузить данные с диска";

    static final String BOOKS_CATALOGUE_DESCRIPTION = "Картотека книг. Выберите действие:";
    static final String BOOKS_CATALOGUE_FIND_BOOK = "1. Найти книгу";
    static final String BOOKS_CATALOGUE_ADD_BOOK = "2. Добавить книгу";
    static final String BOOKS_CATALOGUE_REMOVE_BOOK = "3. Удалить книгу"; //TODO: Прикрутить к контроллеру функцию удаления книги вместе с ее экземплярами
    static final String BOOKS_CATALOGUE_CREATE_CATALOGUE = "4. Создать новый каталог";

    static final String FIND_BOOK_MENU_DESCRIPTION = "Введите автора книги, ее название и год издания";


    static final String COPIES_CATALOGUE_DESCRIPTION = "Картотека экземпляров. Выберите действие:";
    static final String COPIES_CATALOGUE_FIND_COPY = "1. Найти экземпляр книги";
    static final String COPIES_CATALOGUE_FIND_ALL_COPIES = "2. Найти все экземпляры книги";
    static final String COPIES_CATALOGUE_ADD_COPY = "3. Добавить экземпляр книги";
    static final String COPIES_CATALOGUE_REMOVE_COPY = "4. Удалить экземпляр книги";
    static final String COPIES_CATALOGUE_CREATE_CATALOGUE = "5. Создать новый каталог";
}
