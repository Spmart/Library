package library.Observer;

/**
 * Created by Кирилл on 29.11.2016.
 */
public interface Observable {
    public void notifyListeners();
    public void addListener(Observer observer);
    public void deleteListener(Observer observer);
}
