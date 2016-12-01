package library.view.Dialog;

import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Кирилл on 30.11.2016.
 */
public class PaneDialogForAddBook extends JPanel{
    private  JTextField nameBook;
    private  JTextField author;
    private  JTextField yearsPublisher;
    private  JTextField pages;


    public PaneDialogForAddBook()
    {
        nameBook=new JTextField(20);
        author=new JTextField(20);
        yearsPublisher=new JTextField(20);
        pages=new JTextField(20);
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
        add(new Label("Количество страниц"), new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(pages, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }
    public boolean correctEnter() {
        return  !nameBook.getText().isEmpty() &&
                !author.getText().isEmpty()&&
                !yearsPublisher.getText().isEmpty()&&
                !pages.getText().isEmpty();
    }
    public void clearData()
    {
        nameBook.setText("");
        author.setText("");
        yearsPublisher.setText("");
        pages.setText("");
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
    public int getPages()
    {
        return Integer.parseInt(pages.getText());
    }

}
