package com.sowmi.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginScreen {
    private JFrame frame;
    private JLabel userNameLabel, passwordLabel, radioLabel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JRadioButton studentButton, librarianButton;

    public LoginScreen() {
        frame = new JFrame("Login");
        userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(30, 15, 100, 30);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 50, 100, 30);

        radioLabel = new JLabel("Role");
        radioLabel.setBounds(30, 85, 100, 30);

        userNameField = new JTextField();
        userNameField.setBounds(110, 15, 200, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 50, 200, 30);

        studentButton = new JRadioButton("Student");
        studentButton.setBounds(110, 85, 100, 30);

        librarianButton = new JRadioButton("Librarian");
        librarianButton.setBounds(220, 85, 100, 30);


        JButton loginButton = new JButton("Login");
        loginButton.setBounds(130, 125, 80, 25);
        loginButton.addActionListener(this::loginButtonAction);

        frame.add(userNameLabel);
        frame.add(userNameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(radioLabel);
        frame.add(studentButton);
        frame.add(librarianButton);

        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    private void loginButtonAction(ActionEvent actionEvent) {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        Boolean student = studentButton.isSelected();
        Boolean librarian = librarianButton.isSelected();
        if (userName == null || userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter username");
        } else if (password == null || password.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter Password");
        } else if (student && librarian) {
            JOptionPane.showMessageDialog(null, "Only one role should be selected");
        } else if (!student && !librarian) {
            JOptionPane.showMessageDialog(null, "Please select any one role");
        } else {
            Connection connection = ConnectionUtils.getConnection();
            if (connection != null) {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from users where user_name='" + userName + "' and password='" + password + "'");
                    if (!resultSet.next()) {
                        System.out.print("No user");
                        JOptionPane.showMessageDialog(null, "Wrong Username/Password!");
                    } else {
                        String userRole = resultSet.getString("user_role");
                        if ((Objects.equals(userRole, "1") && librarian) || (Objects.equals(userRole, "2") && student)) {
                            JOptionPane.showMessageDialog(null, "Invalid roles selected");
                        }
                        frame.dispose();
                        if (student) {
                            new StudentDashBoard(resultSet.getString("reg_no"));
                        } else {
                            System.out.println(userName + ": " + userRole);
                            new LibrarianScreen(userName);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }


    }
}
