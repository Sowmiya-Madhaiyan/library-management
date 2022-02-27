package com.sowmi.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ReturnBook {

    JFrame jFrame;
    private JLabel  bookLabel;
    JComboBox<String> booksDropDown;

    public ReturnBook(){
        List<String> bookList = ConnectionUtils.getIssuedBooksToReturn();
        String[] books=bookList.toArray(new String[0]);

        jFrame = new JFrame("Return Book");

        bookLabel = new JLabel("Book : ");
        bookLabel.setBounds(30, 50, 120, 30);

        booksDropDown = new JComboBox<>(books);
        booksDropDown.setBounds(100, 50, 140, 30);


        JButton jButton = new JButton("Submit");
        jButton.setBounds(70, 140, 90, 25);
        jButton.addActionListener(this::returnBook);


        jFrame.add(jButton);
        jFrame.add(bookLabel);
        jFrame.add(booksDropDown);

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);
    }

    private void returnBook(ActionEvent actionEvent) {
        String bookName= booksDropDown.getItemAt(booksDropDown.getSelectedIndex());
        Boolean isBookIssued=ConnectionUtils.returnBook(bookName);
        if(isBookIssued){
            JOptionPane.showMessageDialog(null, "Book returned SuccessFully");
        }else{
            JOptionPane.showMessageDialog(null, "Book Not returned");
        }
        jFrame.dispose();
    }
}

