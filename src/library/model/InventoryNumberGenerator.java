package library.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Кирилл on 22.11.2016.
 */
public class InventoryNumberGenerator {
    private static final int MAX_VALUE = 999999;
    private static final int MIN_VALUE = 100000;
    private static ArrayList<BooksCopy> examplesBooks = null;

    /**
     * установка колекции экзепляров
     *
     * @param Books
     */
    public static void setExamplesBooks(ArrayList<BooksCopy> Books) {
        examplesBooks = Books;
    }

    /**
     * проверка на корректность формата инвентарного номера
     *
     * @param inventoryNumber проверямый инвентарный номер
     * @return true если корректный false если нет
     */
    public static boolean correctInventoryNumber(int inventoryNumber) {
        return (inventoryNumber >= MIN_VALUE && inventoryNumber <= MIN_VALUE);
    }

    /**
     * Метод проверки на занятость инвентарного номера
     *
     * @param inventoryNumber инвентарный номер
     * @return true если занят false если нет
     */
    public static boolean isExistByInventoryNumber(int inventoryNumber) {
        for (BooksCopy examplebook : examplesBooks) {
            if (examplebook.getInventoryNumber() == inventoryNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод генерирования инвентарного номера для экзепляра книги
     *
     * @return
     */
    public static int generate() {
        int[] masNumbers = new int[examplesBooks.size()];
        int index = 0;
        if (masNumbers.length > 0) {
            for (BooksCopy booksCopy : examplesBooks) {
                masNumbers[index] = booksCopy.getInventoryNumber();
                index++;
            }
            Arrays.sort(masNumbers);
            for (index = 0; index + 1 < masNumbers.length; index++) {
                if (masNumbers[index + 1] - masNumbers[index] > 1) {
                    return masNumbers[index] + 1;
                }
            }
            return masNumbers[index + 1] + 1;
        } else
            return MIN_VALUE;
    }

}