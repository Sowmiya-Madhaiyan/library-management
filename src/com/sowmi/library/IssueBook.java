package com.sowmi.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class IssueBook {
    JFrame jFrame;
    private JLabel userNameLabel, bookLabel;
    JComboBox<String> studentsDropDown,booksDropDown;

    public IssueBook(){
        List<String> bookList = ConnectionUtils.getAvailableBooksToIssue();
        List<String> studentList=ConnectionUtils.getAllUsers();

        String[] students= studentList.toArray(new String[0]);
        String[] availableBooks=bookList.toArray(new String[0]);

       jFrame = new JFrame("Issue Book");

        userNameLabel = new JLabel("Student: ");
        userNameLabel.setBounds(30, 50, 120, 30);

        bookLabel = new JLabel("Book: ");
        bookLabel.setBounds(30, 90, 120, 30);

        studentsDropDown = new JComboBox<>(students);
        studentsDropDown.setBounds(100, 50, 140, 30);

        booksDropDown = new JComboBox<>(availableBooks);
        booksDropDown.setBounds(100, 90, 140, 30);

        JButton jButton = new JButton("Submit");
        jButton.setBounds(110, 140, 90, 25);
        jButton.addActionListener(this::issueBook);

        JLabel jLabel = new JLabel();
        jLabel.setBounds(90, 100, 400, 100);

        jFrame.add(jButton);
        jFrame.add(userNameLabel);
        jFrame.add(bookLabel);
        jFrame.add(studentsDropDown);
        jFrame.add(booksDropDown);
        jFrame.add(jLabel);

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);
    }

    private void issueBook(ActionEvent actionEvent) {
       String userName= studentsDropDown.getItemAt(studentsDropDown.getSelectedIndex());
       String bookName= booksDropDown.getItemAt(booksDropDown.getSelectedIndex());
       Boolean isBookIssued=ConnectionUtils.issueBook(userName,bookName);
       if(isBookIssued){
           JOptionPane.showMessageDialog(null, "Book Issued SuccessFully");
       }else{
           JOptionPane.showMessageDialog(null, "Book Not issued");
       }
       jFrame.dispose();
    }
}
