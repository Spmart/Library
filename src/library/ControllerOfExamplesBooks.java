package library;

import java.io.*;
import java.util.Vector;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class ControllerOfExamplesBooks {
    //массив экземплятров книг
    private Vector<ExampleBook> examplesBooks = null;
    //поле для выдачи айди
    private static int idExampleBook;

    public ControllerOfExamplesBooks() {
        try {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream("ExamplesBooks.bin"));
            examplesBooks = (Vector<ExampleBook>) (inputFile.readObject());
            inputFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (examplesBooks == null) {
            examplesBooks = new Vector<ExampleBook>();
        }
    }

    //гет для массива экзепляра книг
    public Vector<ExampleBook> getExamplesBooks() {
        return examplesBooks;
    }

    //поиск индекса экзепляра в массиве по айди
    private int getIndexInMasById(int id) {
        int length = examplesBooks.capacity();
        for (int i = 0; i < length; i++) {
            if (examplesBooks.get(i).getIdExampleBook() == id)
                return i;
        }
        return (-1);
    }

    //сохранить в файл текущий массив экзепляров
    public void save() {
        try {
            ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("ExamplesBooks"));
            outFile.writeObject(examplesBooks);
            outFile.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // добавить экзепляр
    public void addExampleBook(int idBook, boolean issued) {
        idExampleBook++;
        examplesBooks.add(new ExampleBook(idExampleBook, idBook, issued));
    }

    //удалить экзепляр по айди
    public void removeExampleBookById(int id) {
        int index = getIndexInMasById(id);
        if (index > -1)
            examplesBooks.remove(index);
    }

    //удалить экзепляр по индексу
    public void removeExampleBookByIndex(int index) {
        examplesBooks.remove(index);
    }

    //количество записей
    public int length() {
        return examplesBooks.size();
    }

    //получить записть по айди
    public ExampleBook getExampleBookById(int id) {
        int index = getIndexInMasById(id);
        if (index > -1) {
            return examplesBooks.get(index);
        } else
            return null;
    }

    // Получить запись по индексу
    public ExampleBook getExampleBookByIndex(int index) {
        return examplesBooks.get(index);
    }


}
