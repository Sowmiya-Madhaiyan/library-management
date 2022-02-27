package com.sowmi.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashBoard {
    private JFrame frame;
    public StudentDashBoard(String regNo) {
        frame = new JFrame("Student DashBoard");

        JButton button1 = new JButton("View Books");
        button1.setBounds(120, 30, 150, 25);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentScreen(regNo);
            }
        });

        frame.add(button1);

        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

}
