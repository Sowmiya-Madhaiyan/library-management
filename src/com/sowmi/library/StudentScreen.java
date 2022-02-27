package com.sowmi.library;

import javax.swing.*;

public class StudentScreen {
    JFrame frame;
    JTable j;
    public StudentScreen(String userName) {
        {
            frame = new JFrame();
            frame.setTitle("Borrowed Books");
            String[][] data = ConnectionUtils.getStudentIssuedBooks(userName);
            String[] columnNames = { "S.No","Book Name", "Issued Date"};
            j = new JTable(data, columnNames);
            j.setBounds(30, 100, 200, 300);
            JScrollPane sp = new JScrollPane(j);
            frame.add(sp);
            frame.setSize(500, 300);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);
        }
    }

}


