package com.sowmi.library;

import javax.swing.*;

public class ViewBooks {
    JFrame frame;
    JTable j;

    public ViewBooks(){
        frame = new JFrame();
        frame.setTitle("Available Books");
        String[][] data = ConnectionUtils.getAvailableBooks();
        String[] columnNames = { "S.No","Book Name"};
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
