package library.controller;

import library.model.ExampleBook;
import library.model.InventoryNumberGenerator;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class CopiesCatalogue {
    private ArrayList<ExampleBook> examplesBooks=null;
    private String nameFileForExamples="ExamplesBooks.bin";


    public CopiesCatalogue()throws java.lang.ClassNotFoundException,java.io.IOException
    {
        try
        {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(nameFileForExamples));
            examplesBooks=(ArrayList<ExampleBook>) (inputFile.readObject());
            inputFile.close();
        }
        catch (Exception e)
        {
            throw e;
        }
        if (examplesBooks==null)
        {
            examplesBooks=new ArrayList<ExampleBook>();
        }
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     * Конструктор инициализацией массива экзепляров из файла
     * @param fileExamplesBooks имя файла , из которого нужно считать массив экземляров
     */
    public CopiesCatalogue(String fileExamplesBooks)throws java.lang.ClassNotFoundException,java.io.IOException
    {
        ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(fileExamplesBooks));
        examplesBooks=(ArrayList<ExampleBook>) (inputFile.readObject());
        inputFile.close();

        if (examplesBooks==null)
        {
            examplesBooks=new ArrayList<ExampleBook>();
        }
        nameFileForExamples=fileExamplesBooks;
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     * Возвращает все экзепляры книг
     * @return examplesBooks
     */
    public ArrayList<ExampleBook> getExamplesBooks()
    {
       return examplesBooks;
    }

    /**
     * Метод для поиска индеса в массиве по inventoryNumber
     * @param inventoryNumber инвентраный номер экзепляра книги
     * @return index
     */
    private int getIndexInMasByInventoryNumber(int inventoryNumber)
    {
        int index=0;
        for (ExampleBook exampleBook: examplesBooks)
        {
            if(exampleBook.getInventoryNumber()==inventoryNumber)
                return index;
            index++;
        }
        throw new InventoryNumberException(InventoryNumberException.NOT_EXIST);
    }

    /**
     * Сеттер для поля nameFileForExamples
     * @param nameFileForExamples имя устанавливаемого файла
     */
    public void setNameFileForExamples(String nameFileForExamples) {
        this.nameFileForExamples = nameFileForExamples;
    }

    /**
     * Геттер для поля nameFileForExamples
     * @return nameFileForExamples
     */
    public String getNameFileForExamples() {
        return nameFileForExamples;
    }

    /**
     * сохраняет экзепляры книг в файл
     */
    public void save() throws java.io.IOException
    {
        ObjectOutputStream outFile=new ObjectOutputStream(new FileOutputStream(nameFileForExamples));
        outFile.writeObject(examplesBooks);
        outFile.close();

    }

    /**
     *Метод добавления экзепляра книг
     * @param idBook id книги
     * @param issued выдана/не выдана
     */
    public void addExampleBook( int idBook, boolean issued)
    {
        examplesBooks.add(new ExampleBook(idBook,issued));
    }

    /**
     *Удаляет экзепляр книги по полю inventoryNumber
     * @param inventoryNumber
     */
    public void removeExampleBookByInventoryNumber(int inventoryNumber)
    {
        if(InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            int index = getIndexInMasByInventoryNumber(inventoryNumber);
            examplesBooks.remove(index);
        }
        else
            throw new InventoryNumberException(InventoryNumberException.NOT_CORRECT);
    }

    /**
     * Удаляет экзепляр книги по индексу в массиве examplesBooks
     * @param index индекс в массиве examplesBooks
     */
    public void removeExampleBookByIndex(int index)
    {
        examplesBooks.remove(index);
    }

    /**
     * Метод для получения количества записей
     * @return количество записей
     */
    public int length()
    {
        return examplesBooks.size();
    }

    /**
     *  метод для получения экзепляра книги по inventoryNumber
     * @param inventoryNumber инвентарный номер
     * @return Экзепляр книги
     */
    public ExampleBook getExampleBookByInventoryNumber(int inventoryNumber)
    {
        int index=getIndexInMasByInventoryNumber(inventoryNumber);
        return examplesBooks.get(index);

    }

    /**
     * Метод получения экзепляра книги по индексу в examplesBooks
     * @param index индекс
     * @return Экзепляр книги
     */
    public ExampleBook getExampleBookByIndex(int index)
    {
        return examplesBooks.get(index);
    }

    /**
     * Метод установки экзепляра книги по индексу в examplesBooks
     * @param index индекс
     * @param exampleBook устанавливаемый экзепляр книги
     */
    public void setExamplesBooks(int index,ExampleBook exampleBook)
    {
        examplesBooks.set(index,exampleBook);
    }

    /**
     * Метод проверки использования инвентарного номера
     * @param inventoryNumber проверяемый инвернтарный номер
     * @return true если используется false если нет
     */
    public boolean isExistInventoryNumber(int inventoryNumber)
    {
        return InventoryNumberGenerator.isExistByInventoryNumber(inventoryNumber);
    }
}
