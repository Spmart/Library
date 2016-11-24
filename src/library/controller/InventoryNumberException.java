package library.controller;

/**
 * Created by Кирилл on 22.11.2016.
 */
public class InventoryNumberException extends IllegalArgumentException{
    public static final String NOT_EXIST="Несуществующий инвентарный номер";
    public static final String ABOVE_ZERO="Инвентарный номер должен быть больше нуля";
    public static final String EXIST="Существующий инвентарный номер";
    public static final String NOT_CORRECT="Некорректный формат";
    public InventoryNumberException(String s)
    {
        super(s);
    }
}
