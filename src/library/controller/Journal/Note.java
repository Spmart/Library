package library.controller.Journal;

/**
 * Created by Кирилл on 29.11.2016.
 */
public class Note {
    String action;
    public Note(String action)
    {
        this.action=action;
    }
    public String getAction()
    {
        return action;
    }
    public void setAction(String action)
    {
        this.action=action;
    }
}
