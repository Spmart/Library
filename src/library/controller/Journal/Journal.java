package library.controller.Journal;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Кирилл on 29.11.2016.
 */
public class Journal {
    private ArrayList<Note> journal;

    public static final String REMOVED_BOOK="removed book";
    public static final String ADDED_BOOK="added book";
    public static final String UPDATED_BOOK="updated book";

    public static final String REMOVED_EXAMPLE_BOOK="removed example book";
    public static final String ADDED_EXAMPLE_BOOK="added example book";
    public static final String UPDATED_EXAMPLE_BOOK="updated example book";

    public Journal()
    {
        journal=new ArrayList<Note>();
    }
    public void AddNotes(String action)
    {
        journal.add(new Note(action));
    }
    public ArrayList<Note> getJournal()
    {
        return journal;
    }
    public Note getLastNote()
    {
        return journal.get(journal.size()-1);
    }
}
