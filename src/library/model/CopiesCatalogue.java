package library.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class CopiesCatalogue implements Iterable<BooksCopy> {
    private ArrayList<BooksCopy> examplesBooks = null;
    private String nameFileForExamples = "ExamplesBooks.bin";

    public CopiesCatalogue() throws java.lang.ClassNotFoundException, java.io.IOException {
        read();
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     * Конструктор инициализацией массива экзепляров из файла
     *
     * @param fileExamplesBooks имя файла , из которого нужно считать массив экземляров
     */
    public CopiesCatalogue(String fileExamplesBooks) throws java.lang.ClassNotFoundException, java.io.IOException {
        nameFileForExamples = fileExamplesBooks;
        read();
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     * Возвращает все экзепляры книг
     *
     * @return examplesBooks
     */
    public ArrayList<BooksCopy> getExamplesBooks() {
        return examplesBooks;
    }

    /**
     * Сеттер для поля nameFileForExamples
     *
     * @param nameFileForExamples имя устанавливаемого файла
     */
    public void setNameFileForExamples(String nameFileForExamples) {
        this.nameFileForExamples = nameFileForExamples;
    }

    /**
     * Геттер для поля nameFileForExamples
     *
     * @return nameFileForExamples
     */
    public String getNameFileForExamples() {
        return nameFileForExamples;
    }

    /**
     * Метод чтения из файла
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(nameFileForExamples));
        examplesBooks = (ArrayList<BooksCopy>) (inputFile.readObject());
        inputFile.close();
        if (examplesBooks == null) {
            examplesBooks = new ArrayList<BooksCopy>();
        }
    }

    /**
     * Метод чтения из файла
     *
     * @param fileExamplesBooks имя файла
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void read(String fileExamplesBooks) throws IOException, ClassNotFoundException {
        setNameFileForExamples(fileExamplesBooks);
        ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(nameFileForExamples));
        examplesBooks = (ArrayList<BooksCopy>) (inputFile.readObject());
        inputFile.close();
        if (examplesBooks == null) {
            examplesBooks = new ArrayList<BooksCopy>();
        }
    }

    /**
     * сохраняет экзепляры книг в файл
     */
    public void save() throws java.io.IOException {
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(nameFileForExamples));
        outFile.writeObject(examplesBooks);
        outFile.close();

    }

    /**
     * Метод добавления экзепляра книг
     *
     * @param idBook id книги
     * @param issued выдана/не выдана
     */
    public void addExampleBook(int idBook, boolean issued) {
        examplesBooks.add(new BooksCopy(idBook, issued));
    }

    /**
     * Метод добавления экзепляра книг
     *
     * @param idBook          id книги
     * @param issued          выдана/ не выдана
     * @param inventoryNumber ее инвентарный номер
     */
    public void addExampleBook(int idBook, boolean issued, int inventoryNumber) {
        examplesBooks.add(new BooksCopy(inventoryNumber, idBook, issued));
    }

    /**
     * Удаляет экзепляр книги по полю inventoryNumber
     *
     * @param inventoryNumber
     */
    public void removeExampleBookByInventoryNumber(int inventoryNumber) {
        if (InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            for (BooksCopy booksCopy : examplesBooks) {
                if (booksCopy.getInventoryNumber() == inventoryNumber) {
                    examplesBooks.remove(booksCopy);
                    return;
                }
            }
            throw new IllegalArgumentException("Неверный ввод!");
        } else
            throw new IllegalArgumentException("Неверный ввод!");
    }

    /**
     * Удаляет экзепляр книги по индексу в массиве examplesBooks
     *
     * @param index индекс в массиве examplesBooks
     */
    public void removeExampleBookByIndex(int index) {
        examplesBooks.remove(index);
    }

    /**
     * Метод для получения количества записей
     *
     * @return количество записей
     */
    public int length() {
        return examplesBooks.size();
    }

    /**
     * метод для получения экзепляра книги по inventoryNumber
     *
     * @param inventoryNumber инвентарный номер
     * @return Экзепляр книги
     */
    public BooksCopy getExampleBookByInventoryNumber(int inventoryNumber) {
        if (InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            for (BooksCopy booksCopy : examplesBooks) {
                if (booksCopy.getInventoryNumber() == inventoryNumber) {
                    return booksCopy;
                }
            }
            throw new IllegalArgumentException("Неверный ввод!");
        } else
            throw new IllegalArgumentException("Неверный ввод!");

    }

    /**
     * Метод получения экзепляра книги по индексу в examplesBooks
     *
     * @param index индекс
     * @return Экзепляр книги
     */
    public BooksCopy getExampleBookByIndex(int index) {
        return examplesBooks.get(index);
    }

    /**
     * Метод установки экзепляра книги по индексу в examplesBooks
     *
     * @param index       индекс
     * @param booksCopy устанавливаемый экзепляр книги
     */
    public void setExamplesBooks(int index, BooksCopy booksCopy) {
        examplesBooks.set(index, booksCopy);
    }

    /**
     * Метод проверки использования инвентарного номера
     *
     * @param inventoryNumber проверяемый инвернтарный номер
     * @return true если используется false если нет
     */
    public boolean isExistInventoryNumber(int inventoryNumber) {
        return InventoryNumberGenerator.isExistByInventoryNumber(inventoryNumber);
    }

    private class IteratorExampleBook implements Iterator<BooksCopy> {
        int index = -1;

        @Override
        public boolean hasNext() {
            return (index + 1) < examplesBooks.size();
        }

        @Override
        public BooksCopy next() {
            index++;
            return examplesBooks.get(index);
        }
    }

    @Override
    public Iterator<BooksCopy> iterator() {
        return new IteratorExampleBook();
    }

}