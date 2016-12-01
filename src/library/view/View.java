package library.view;

import javafx.scene.control.*;
import library.Observer.Observer;
import library.controller.DataProvider;
import library.controller.Journal.Journal;
import library.controller.Journal.Note;
import library.model.Book;
import library.model.ExampleBook;
import library.view.Dialog.PaneDialogForAddBook;
import library.view.Dialog.PaneDialogForAddExampleBook;
import library.view.Dialog.PaneDialogForUpdateBook;
import library.view.Dialog.PaneDialogForUpdateExampleBook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Кирилл on 29.11.2016.
 */
public class View extends JFrame implements ActionListener,Observer {

    private JTable tableBooks;
    private JTable tableExampleBooks;

    private DefaultTableModel modelForBooks;
    private DefaultTableModel modelForExamplesBooks;

    private String[] headTableBooks={"Id","Название","Автор","Год публикации","Количетсво страниц"};
    private String[] headTableExamplesBooks={"Инвентарынй номер","Выдана","Id Книги"};

    private DataProvider dataProvider;

    private Container container;

    private JButton buttonAddBook;
    private JButton buttonDeleteBook;
    private JButton buttonUpdateBook;
    private JButton buttonAddExampleBook;
    private JButton buttonDeleteExampleBook;
    private JButton buttonUpdateExampleBook;

    private PaneDialogForAddBook paneDialogForAddBook;
    private PaneDialogForAddExampleBook paneDialogForAddExampleBook;
    private PaneDialogForUpdateExampleBook paneDialogForUpdateExampleBook;
    private PaneDialogForUpdateBook paneDialogForUpdateBook;

    public View()
    {
        super();
        init();
    }

    /**
     * Метод подготовливает данные для вставки ряда в модель таблицы modelForBooks
     * @param book подготавлеваемая книга
     * @return массив из данных книги
     */
    private String[] bookInRowModelTable(Book book)
    {
        return new String[]{""+book.getId(),book.getName(),book.getAuthors(),""+book.getPublishingYear(),""+book.getPagesQuantity()};
    }

    /**
     * Метод подготавливает данных для вставки ряда в модел таблицы modelForExamplesBooks
     * @param exampleBook
     * @return
     */
    private String[] exampleBookInRowModelTable(ExampleBook exampleBook)
    {
        String[] result = new String[3];
        result[0]=exampleBook.getInventoryNumber()+"";
        if(exampleBook.getIssued())
        {
            result[1]="да";
        }
        else
        {
            result[1]="нет";
        }
        result[2]=exampleBook.getIdBook()+"";
        return result;
    }

    /**
     * Общая инициализация
     */
    private void init()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container=getContentPane();
        container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));
        dataProvider= new DataProvider();
        dataProvider.addListener(this);
        try
        {
            dataProvider.readFromFile();
        }
        catch (IOException e)
        {

        }
        catch (ClassNotFoundException e)
        {

        }
        initShowDialogs();//инициализация деалоговых окон
        initContainerBooks();//Инициализация контейнера содержащего таблицу книг и кнопки для этой таблицы
        initContainerExamplesBooks();//Инициализация контейнера содержащего таблицу  экземпляров книг и кнопки для этой таблицы
        setSize(300,300);
        setVisible(true);
    }

    /**
     * Метод инициализации диалоговых окон
     */
    private void initShowDialogs()
    {
        paneDialogForAddBook=new PaneDialogForAddBook();
        paneDialogForAddExampleBook=new PaneDialogForAddExampleBook();
        paneDialogForUpdateExampleBook= new PaneDialogForUpdateExampleBook();
        paneDialogForUpdateBook=new PaneDialogForUpdateBook();
    }

    /**
     * метод инициализации контейнера для таблицы книг и ее кнопок
     */
    private void initContainerBooks()
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        initTableBook(panel);// инициализация таблицы книг
        initButtonsBook(panel);// инициализация кнопок для этой таблицы
        container.add(panel);
    }

    /**
     * Метон инициализации таблицы книг
     * @param panel родительская панель для таблицы
     */
    private void initTableBook(JPanel panel)
    {
        modelForBooks=new DefaultTableModel();//создание модели для таблицы книг
        modelForBooks.setColumnIdentifiers(headTableBooks);//заголовки таблицы
        for (Book book: dataProvider.getBooks()) {
            modelForBooks.addRow(bookInRowModelTable(book));
        }//наполнение данными
        tableBooks=new JTable(modelForBooks){
            @Override
            public boolean isCellEditable ( int row, int column )
            {
                return false;
            }
        };//создание самой таблицы (переопределение метода для невозмжных изменений)
        panel.add(new JScrollPane(tableBooks));
    }

    /**
     * Метод инициализации кнопок для таблицы книг
     * @param panel родительская панель
     */
    private void initButtonsBook(JPanel panel)
    {
        JPanel panelButton=new JPanel();
        panelButton.setLayout(new FlowLayout());
        buttonAddBook=new JButton("Добавить");
        buttonDeleteBook=new JButton("Удалить");
        buttonUpdateBook=new JButton("Изменить");
        buttonAddBook.addActionListener(this);//
        buttonDeleteBook.addActionListener(this);//слушатель событий этот фрейм
        buttonUpdateBook.addActionListener(this);//
        panelButton.add(buttonAddBook);
        panelButton.add(buttonDeleteBook);
        panelButton.add(buttonUpdateBook);
        panel.add(panelButton);
    }

    /**
     * Метод инициализации контейнера с таблицо экзепляров книг и ее кнопок
     */
    private void initContainerExamplesBooks()
    {
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        initTableExamplesBooks(panel);//вызов инициализации таблицы экземпляров книг
        initButtonsExamplesBooks(panel);// вызов инициализации кнопок для этой таблицы
        container.add(panel);
    }

    /**
     * Метод инициализации таблицы экзепляров книг
     * @param panel родительская панель
     */
    private void initTableExamplesBooks(JPanel panel)
    {
        modelForExamplesBooks=new DefaultTableModel();//Инициализация модели для таблицы
        modelForExamplesBooks.setColumnIdentifiers(headTableExamplesBooks);//Заголовки таблицы
        for (ExampleBook exampleBook: dataProvider.getExamplesBooks()) {
            modelForExamplesBooks.addRow(exampleBookInRowModelTable(exampleBook));
        }//Наполнение данными
        tableExampleBooks=new JTable(modelForExamplesBooks){
            @Override
            public boolean isCellEditable ( int row, int column )
            {
                return false;
            }
        };//Создание самой таблицы без права изменений ячеек
        panel.add(new JScrollPane(tableExampleBooks));
    }

    /**
     * Метод инициализации кнопок таблицы экзепляров
     * @param panel родительская панель
     */
    private void initButtonsExamplesBooks(JPanel panel)
    {
        JPanel panelButton=new JPanel();
        panelButton.setLayout(new FlowLayout());
        buttonAddExampleBook=new JButton("Добавить");
        buttonDeleteExampleBook=new JButton("Удалить");
        buttonUpdateExampleBook=new JButton("Изменить");
        buttonAddExampleBook.addActionListener(this);//
        buttonDeleteExampleBook.addActionListener(this);// слушатель кнопок - этот фрейм
        buttonUpdateExampleBook.addActionListener(this);//
        panelButton.add(buttonAddExampleBook);
        panelButton.add(buttonDeleteExampleBook);
        panelButton.add(buttonUpdateExampleBook);
        panel.add(panelButton);
    }

    public static void main (String[] arg)
    {
        View app=new View();
        app.pack();
        app.setVisible(true);
    }

    /**
     * метод обновляет все данные таблицы книг
     */
    private void refreshTableBook()
    {
        modelForBooks=new DefaultTableModel();
        modelForBooks.setColumnIdentifiers(headTableBooks);//инициализация новой модели
        for (Book book:dataProvider.getBooks()) {
            modelForBooks.addRow(bookInRowModelTable(book));
        }
        tableBooks.setModel(modelForBooks);
    }

    /**
     * метод обновляет все данные таблицы экзепляров
     */
    private void refreshTableExampleBook()
    {
        modelForExamplesBooks=new DefaultTableModel();//
        modelForExamplesBooks.setColumnIdentifiers(headTableExamplesBooks);//инициализация новой модели
        for (ExampleBook exampleBook:dataProvider.getExamplesBooks()) {
            modelForExamplesBooks.addRow(exampleBookInRowModelTable(exampleBook));
        }
        tableExampleBooks.setModel(modelForExamplesBooks);
    }

    /**
     * Метод обработки нажатий на кнопки
     * @param e прослущаемое событие
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Удаление книги
        if (e.getSource().equals(buttonDeleteBook)) {
            int idBook = Integer.parseInt((String) (tableBooks.getValueAt(tableBooks.getSelectedRow(), 0)));//id выделенной киниг в таблице
            try
            {
                dataProvider.removeBook(idBook);
            }
            catch (Exception err)
            {
                JOptionPane.showMessageDialog(this,
                        err.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        //Добавление книги
        if (e.getSource().equals(buttonAddBook)) {
            int res = showDialogForAddBook();
            if (res == JOptionPane.OK_OPTION) {
                try {
                    dataProvider.addBook(paneDialogForAddBook.getAuthor(), paneDialogForAddBook.getName(), paneDialogForAddBook.getYearsPublisher(), paneDialogForAddBook.getPages());
                }
                catch (NumberFormatException errNumber) {
                    JOptionPane.showMessageDialog(this,
                            "Неверный тип данных(символы вместо цифр)",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception err) {
                    JOptionPane.showMessageDialog(this,
                            err.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            return;
        }
        //Изменение книги
        if (e.getSource().equals(buttonUpdateBook)) {
            if (tableBooks.getSelectedRow() > -1) {
                int idBook = Integer.parseInt((String) (tableBooks.getValueAt(tableBooks.getSelectedRow(), 0)));//id выделенной книги в таблице
                int res=showDialogForUpdateBook(idBook);
                if (res == JOptionPane.OK_OPTION) {
                    try {
                        Book newBook = new Book(-1, paneDialogForUpdateBook.getAuthor(), paneDialogForUpdateBook.getName(), paneDialogForUpdateBook.getYearPublishing(), paneDialogForUpdateBook.getPages());
                        dataProvider.setBook(idBook, newBook);
                    }
                    catch (NumberFormatException errNumber)
                    {
                        JOptionPane.showMessageDialog(this,
                                "Неверный тип данных(символы вместо цифр)",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    catch (Exception err)
                    {
                        JOptionPane.showMessageDialog(this,
                                err.getMessage(),
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                return;
            }
        }
        //Добавление экземпляра книги
        if (e.getSource().equals(buttonAddExampleBook)) {

            int res = showDialogForAddExampleBook();
            if (res == JOptionPane.OK_OPTION) {
                try {
                    dataProvider.addExampleBook(paneDialogForAddExampleBook.getAuthor(), paneDialogForAddExampleBook.getName(), paneDialogForAddExampleBook.getYearsPublisher(), paneDialogForAddExampleBook.getIssue());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(this,
                            err.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            return;
        }
        //Удаление экземпляра книги
        if (e.getSource().equals(buttonDeleteExampleBook)) {
            if (tableExampleBooks.getSelectedRow() > -1) {
                int inventoryNumber = Integer.parseInt((String) (tableExampleBooks.getValueAt(tableExampleBooks.getSelectedRow(), 0)));
                dataProvider.removeExampleBook(inventoryNumber);
            }
            return;
        }
        //Изменение экземпляра книги
        if (e.getSource().equals(buttonUpdateExampleBook)) {
            if (tableExampleBooks.getSelectedRow() > -1) {
                int inventoryNumber = Integer.parseInt((String) (tableExampleBooks.getValueAt(tableExampleBooks.getSelectedRow(), 0)));
                int res=showDialogForUpdateExampleBook(inventoryNumber);
                if (res == JOptionPane.OK_OPTION) {
                    ExampleBook newExampleBook = new ExampleBook(dataProvider.getExampleBook(inventoryNumber).getIdBook(), paneDialogForUpdateExampleBook.getIssue());
                    try {
                        dataProvider.setExampleBook(inventoryNumber, newExampleBook);
                    }
                    catch (Exception err)
                    {
                        JOptionPane.showMessageDialog(this,
                                err.getMessage(),
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            return;
        }
    }

    /**
     * Метод показывает всплывающее окно для добавления книги
     * @return возвращается число равное результату вызова(нажатия кнопки Ок или Отмена)
     */
    private int showDialogForAddBook()
    {
        paneDialogForAddBook.clearData();
        return JOptionPane.showConfirmDialog(this, paneDialogForAddBook, "Enter data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Метод показывает всплывающее окно для изменения книги
     * @param idBook
     * @return возвращается число равное результату вызова(нажатия кнопки Ок или Отмена)
     */
    private int showDialogForUpdateBook(int idBook)
    {
        Book book = dataProvider.getBook(idBook);
        paneDialogForUpdateBook.refreshData(book.getName(), book.getAuthors(), book.getPublishingYear(), book.getPagesQuantity());
        return JOptionPane.showConfirmDialog(this, paneDialogForUpdateBook, "Enter data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Метод показывает окно для добавления экземпляра книги
     * @return возвращается число равное результату вызова(нажатия кнопки Ок или Отмена)
     */
    private int showDialogForAddExampleBook()
    {
        if (tableBooks.getSelectedRow() > -1) {
            String[] selectedBook = {
                    (String) (tableBooks.getValueAt(tableBooks.getSelectedRow(), 1)),
                    (String) (tableBooks.getValueAt(tableBooks.getSelectedRow(), 2)),
                    (String) (tableBooks.getValueAt(tableBooks.getSelectedRow(), 3)),
            };
            paneDialogForAddExampleBook.refreshData(selectedBook[0], selectedBook[1], selectedBook[2]);
        }
        else
            paneDialogForAddExampleBook.clearData();
        return   JOptionPane.showConfirmDialog(this, paneDialogForAddExampleBook, "Enter data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Метод показывает окно для изменения экзепляра книги
     * @param inventoryNumber инвентарный номер изменяемого экземпляра
     * @return @return возвращается число равное результату вызова(нажатия кнопки Ок или Отмена)
     */
    private int showDialogForUpdateExampleBook(int inventoryNumber)
    {
        Book book = dataProvider.getBook((dataProvider.getExampleBook(inventoryNumber)).getIdBook());
        ExampleBook exampleBook = dataProvider.getExampleBook(inventoryNumber);
        paneDialogForUpdateExampleBook.refreshData(book.getName(), book.getAuthors(), book.getPublishingYear(), exampleBook.getIssued());
        return JOptionPane.showConfirmDialog(this, paneDialogForUpdateExampleBook, "Enter data",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Метод прослушивания событий DataProvider
     */
    @Override
    public void listenEvent() {
        Note lastNote=dataProvider.getLastNotes();//последнее событие
        switch (lastNote.getAction())
        {
            //событие-добавление книги
            case Journal.ADDED_BOOK:
                refreshTableBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
            //событие-удаление книги
            case Journal.REMOVED_BOOK:
                refreshTableBook();
                refreshTableExampleBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
            //событие-изменение книги
            case Journal.UPDATED_BOOK:
                refreshTableBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
            //событие-добавление экземпляра
            case Journal.ADDED_EXAMPLE_BOOK:
                refreshTableExampleBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
            //событие-удаление экземпляра
            case Journal.REMOVED_EXAMPLE_BOOK:
                refreshTableExampleBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
            //событие-изменение экземпляра
            case Journal.UPDATED_EXAMPLE_BOOK:
                refreshTableExampleBook();
                try {
                    dataProvider.writeToFile();
                }
                catch (IOException e)
                {

                }
                catch (ClassCastException e)
                {

                }
                break;
        }
    }

}
