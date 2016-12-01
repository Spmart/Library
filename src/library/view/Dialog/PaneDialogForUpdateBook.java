package library.view.Dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Кирилл on 30.11.2016.
 */
public class PaneDialogForUpdateBook extends JPanel{
    private  JTextField nameBook;
    private  JTextField author;
    private  JTextField yearPublishing;
    private  JTextField pages;


    public PaneDialogForUpdateBook()
    {
        nameBook=new JTextField(20);
        author=new JTextField(20);
        yearPublishing =new JTextField(20);
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
        add(yearPublishing, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        add(new Label("Количество страниц"), new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
        add(pages, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    }
    public boolean correctEnter()
    {
        return (nameBook.getText()!=null&&nameBook.getText()!="")&&(author.getText()!=null&&author.getText()!="")&&(yearPublishing.getText()!=null&&yearPublishing.getText()!="")&&(pages.getText()!=null&&pages.getText()!="");
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
    public int getPages()
    {
        return Integer.parseInt(pages.getText());
    }
    public void refreshData(String nameBook,String author,int yearPublishing,int pages )
    {
        this.nameBook.setText(nameBook);
        this.author.setText(author);
        this.yearPublishing.setText(yearPublishing+"");
        this.pages.setText(pages+"");
    }

}
