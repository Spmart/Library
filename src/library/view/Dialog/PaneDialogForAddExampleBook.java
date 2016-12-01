package library.view.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Кирилл on 30.11.2016.
 */
public class PaneDialogForAddExampleBook extends JPanel {
    private JComboBox issue;

    private  JTextField nameBook;
    private  JTextField author;
    private  JTextField yearsPublisher;

    private final String[] dataForIssue={"Выдана","Не выдана"};
    public PaneDialogForAddExampleBook()
    {
        nameBook=new JTextField(20);
        author=new JTextField(20);
        yearsPublisher=new JTextField(20);
        issue=new JComboBox(dataForIssue);
        setLayout(new GridBagLayout());
        add(new Label("Название книги"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(nameBook, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(new Label("Автор"), new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(author, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(new Label("Год публикации"), new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(yearsPublisher, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(new Label("Статус выдачи"), new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(issue, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    }
    public String getName()
    {
        return nameBook.getText();
    }
    public String  getAuthor()
    {
        return author.getText();
    }
    public int getYearsPublisher()
    {
        return Integer.parseInt(yearsPublisher.getText());
    }
    public boolean getIssue()
    {
        return ((String)(issue.getSelectedItem())).equals(dataForIssue[0]);
    }
    public void refreshData(String nameBook,String author,String yearsPublisher )
    {
        this.nameBook.setText(nameBook);
        this.author.setText(author);
        this.yearsPublisher.setText(yearsPublisher);
    }
    public void clearData()
    {
        nameBook.setText("");
        author.setText("");
        yearsPublisher.setText("");
    }
}
