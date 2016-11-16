package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class ExampleBook implements java.io.Serializable {
    //первичный ключ
    private int idExampleBook = 0;
    //выдана ли книга
    private boolean issued = false;
    //вторичный ключ книги
    private int idBook = 0;

    public ExampleBook(int idExampleBook, int idBook, boolean issued) {
        this.idExampleBook = idExampleBook;
        this.idBook = idBook;
        this.issued = issued;
    }

    // гет  для первичного ключа
    public int getIdExampleBook() {
        return idExampleBook;
    }

    //гет  для вторичного ключа книги
    public int getIdBook() {
        return idBook;
    }

    //гет сет для индикатора наличия
    public boolean getIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

}
