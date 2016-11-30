package library.model;

/**
 * Created by Кирилл on 14.11.2016.
 */
public class BooksCopy implements java.io.Serializable {
    private int inventoryNumber = 0;
    private boolean issued = false;
    private int idBook = 0;

    /**
     * @param inventoryNumber инвектарный номер экзепляра книги
     * @param idBook          id книги
     * @param issued          выдана или нет
     */
    public BooksCopy(int inventoryNumber, int idBook, boolean issued) {
        this.idBook = idBook;
        this.issued = issued;
        setInventoryNumber(inventoryNumber);
    }

    public BooksCopy(int idBook, boolean issued) {
        this.inventoryNumber = InventoryNumberGenerator.generate();
        this.idBook = idBook;
        this.issued = issued;
    }


    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) throws IllegalArgumentException {
        if (InventoryNumberGenerator.correctInventoryNumber(inventoryNumber)) {
            if (!InventoryNumberGenerator.isExistByInventoryNumber(inventoryNumber)) {
                this.inventoryNumber = inventoryNumber;
            } else {
                throw new IllegalArgumentException("Неверный ввод!");
            }
        } else {
            throw new IllegalArgumentException("Неверный ввод!");
        }
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public boolean getIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

}
