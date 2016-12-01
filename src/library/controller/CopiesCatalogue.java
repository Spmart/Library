package library.controller;

import library.model.ExampleBook;
import library.model.InventoryNumberGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class CopiesCatalogue implements Iterable<ExampleBook> {
    private ArrayList<ExampleBook> examplesBooks=null;
    private String nameFileForExamples="ExamplesBooks.bin";


    public CopiesCatalogue()
    {
        try {
            read();
        }
        catch (IOException e)
        {
            examplesBooks=new ArrayList<ExampleBook>();
        }
        catch (ClassNotFoundException e)
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
        try {
            read(fileExamplesBooks);
        }
        catch (IOException e)
        {
            examplesBooks=new ArrayList<ExampleBook>();
        }
        catch (ClassNotFoundException e)
        {
            examplesBooks=new ArrayList<ExampleBook>();
        }
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
     * Метод чтения из файла
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(nameFileForExamples));
        examplesBooks=(ArrayList<ExampleBook>) (inputFile.readObject());
        inputFile.close();
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }
    /**
     * Метод чтения из файла
     * @param fileExamplesBooks имя файла
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void read(String fileExamplesBooks) throws IOException, ClassNotFoundException {
        setNameFileForExamples(fileExamplesBooks);
        ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(nameFileForExamples));
        examplesBooks=(ArrayList<ExampleBook>) (inputFile.readObject());
        inputFile.close();
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
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
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     * Метод добавления экзепляра книг
     * @param idBook id книги
     * @param issued выдана/ не выдана
     * @param inventoryNumber ее инвентарный номер
     */
    public void addExampleBook(int idBook,boolean issued, int inventoryNumber)
    {
        examplesBooks.add(new ExampleBook(inventoryNumber,idBook,issued));
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    /**
     *Удаляет экзепляр книги по полю inventoryNumber
     * @param inventoryNumber
     */
    public void removeExampleBookByInventoryNumber(int inventoryNumber)
    {
        if(InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            for (ExampleBook exampleBook: examplesBooks)
            {
                if(exampleBook.getInventoryNumber()==inventoryNumber)
                {
                    examplesBooks.remove(exampleBook);
                    InventoryNumberGenerator.setExamplesBooks(examplesBooks);
                    return;
                }
            }
            throw new InventoryNumberException(InventoryNumberException.NOT_EXIST);
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
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
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
        if(InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            for (ExampleBook exampleBook : examplesBooks) {
                if (exampleBook.getInventoryNumber() == inventoryNumber) {
                    return exampleBook;
                }
            }
            throw new InventoryNumberException(InventoryNumberException.NOT_EXIST);
        }
        else
            throw new InventoryNumberException(InventoryNumberException.NOT_CORRECT);

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
        InventoryNumberGenerator.setExamplesBooks(examplesBooks);
    }

    public void setExamplesBooksByInventoryNumber(int inventoryNumber , ExampleBook inExampleBook)
    {
        if(InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {

            for (int index=examplesBooks.size()-1;index>-1;index--)
            {
                if(examplesBooks.get(index).getInventoryNumber()==inventoryNumber)
                {
                    examplesBooks.remove(index);
                    InventoryNumberGenerator.setExamplesBooks(examplesBooks);
                    inExampleBook.setInventoryNumber(inventoryNumber);
                    examplesBooks.add(index,inExampleBook);
                    InventoryNumberGenerator.setExamplesBooks(examplesBooks);
                    return;
                }
            }
            throw new InventoryNumberException(InventoryNumberException.NOT_EXIST);
        }
        else
            throw new InventoryNumberException(InventoryNumberException.NOT_CORRECT);
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
    private class IteratorExampleBook implements Iterator<ExampleBook> {
        int index=-1;
        @Override
        public boolean hasNext() {
            return (index+1)<examplesBooks.size();
        }

        @Override
        public ExampleBook next() {
            index++;
            return examplesBooks.get(index);
        }
    }
    @Override
    public Iterator<ExampleBook> iterator() {
        return new IteratorExampleBook();
    }
    public static void main(String[] args)
    {
        CopiesCatalogue copiesCatalogue=null;
        copiesCatalogue = new CopiesCatalogue();


        copiesCatalogue.addExampleBook(0,false);
        copiesCatalogue.addExampleBook(1,false);
        copiesCatalogue.addExampleBook(2,false);
        copiesCatalogue.addExampleBook(3,false);
        copiesCatalogue.addExampleBook(4,false);
        try {
            copiesCatalogue.save();
        }
        catch (IOException e)
        {

        }
    }

}
