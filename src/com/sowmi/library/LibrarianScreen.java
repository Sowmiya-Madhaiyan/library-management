package com.sowmi.library;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LibrarianScreen {
    private JFrame frame;

    public LibrarianScreen(String userName)

    {
        frame = new JFrame("Librarian DashBoard");


        JButton button1 = new JButton("View Books");
        button1.setBounds(10, 30, 120, 25);
        button1.addActionListener(this::viewAvailableBooks);

        JButton button2 = new JButton("Issue Book");
        button2.setBounds(160, 30, 120, 25);
        button2.addActionListener(this::issueBook);

        JButton button3 = new JButton("Return Book");
        button3.setBounds(310, 30, 120, 25);
        button3.addActionListener(this::returnBook);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);


        frame.setSize(470, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    private void returnBook(ActionEvent actionEvent) {
        new ReturnBook();
    }

    private void issueBook(ActionEvent actionEvent) {
        new IssueBook();
    }

    private void viewAvailableBooks(ActionEvent actionEvent) {
        new ViewBooks();
    }
}
