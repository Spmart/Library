package library.view.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Кирилл on 30.11.2016.
 */
public class PaneDialogForUpdateExampleBook extends JPanel {
    private JComboBox issue;

    private  JTextField nameBook;
    private  JTextField author;
    private  JTextField yearPublishing;
    private final String[] dataForIssue={"Выдана","Не выдана"};
    public PaneDialogForUpdateExampleBook()
    {
        nameBook=new JTextField(20);
        author=new JTextField(20);
        yearPublishing =new JTextField(20);
        nameBook.setEditable(false);
        author.setEditable(false);
        yearPublishing.setEditable(false);
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
        add(yearPublishing, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
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
    public int getYearPublishing()
    {
        return Integer.parseInt(yearPublishing.getText());
    }
    public boolean getIssue()
    {
        return ((String)(issue.getSelectedItem())).equals(dataForIssue[0]);
    }
    public void refreshData(String nameBook,String author,int yearsPublisher,boolean issue )
    {
        this.nameBook.setText(nameBook);
        this.author.setText(author);
        this.yearPublishing.setText(yearsPublisher+"");
        if (issue)
        {
            this.issue.setSelectedIndex(0);
        }
        else
        {
            this.issue.setSelectedIndex(1);
        }
    }
}
