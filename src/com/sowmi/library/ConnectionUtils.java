package com.sowmi.library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectionUtils {

    public static Connection getConnection(){
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/library","root","admin");

        }catch(Exception e){ System.out.println(e);}
        return connection;
    }

    public static String[][] getStudentIssuedBooks(String issuedTo){
        Connection connection=getConnection();
        int rowCount=0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultCount=statement.executeQuery("select count(*) from books where available='N' and issued_to='"+issuedTo+"'");
            while(resultCount.next()){
                rowCount=resultCount.getInt(1);
            }
            String[][] result = new String[rowCount][3];
            ResultSet resultSet=statement.executeQuery("select * from books where available='N' and issued_to='"+issuedTo+"'");
            int row = 0;
            int sNo=1;
            while (resultSet.next()){
                result[row][0]=String.valueOf(sNo);
                result[row][1]=resultSet.getString("title");
                result[row][2]=resultSet.getDate("issued_date").toString();
                sNo++;
                row++;
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[][] getAvailableBooks(){
        Connection connection=getConnection();
        int rowCount=0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultCount=statement.executeQuery("select count(*) from books where available='Y'");
            while(resultCount.next()){
                rowCount=resultCount.getInt(1);
            }
            String[][] result = new String[rowCount][2];
            ResultSet resultSet=statement.executeQuery("select * from books where available='Y'");
            int row = 0;
            int sNo=1;
            while (resultSet.next()){
                result[row][0]=String.valueOf(sNo);
                result[row][1]=resultSet.getString("title");
                sNo++;
                row++;
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAvailableBooksToIssue() {
        {
            Connection connection=getConnection();
            List<String> bookList= new ArrayList<>();
            try {
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from books where available='Y'");
                while (resultSet.next()){
                    bookList.add(resultSet.getString("title"));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bookList;
        }
    }

    public static List<String> getIssuedBooksToReturn() {
        {
            Connection connection=getConnection();
            List<String> bookList= new ArrayList<>();
            try {
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from books where available='N'");
                while (resultSet.next()){
                    bookList.add(resultSet.getString("title"));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bookList;
        }
    }
    public static List<String> getAllUsers() {
        {
            Connection connection=getConnection();
            List<String> userList= new ArrayList<>();
            try {
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from users where user_role='1'");
                while (resultSet.next()){
                    userList.add(resultSet.getString("user_name"));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userList;
        }
    }

    public static Boolean issueBook(String userName, String bookName) {
        Connection connection=getConnection();
        try {
            String userId=getUserId(userName);
            PreparedStatement  statement = connection.prepareStatement("update books set available=?,issued_to=?,issued_date=? where title='"+bookName+"'");
            statement.setString(1,"N");
            statement.setString(2,userId);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            int resultSet=statement.executeUpdate();
            connection.close();
            return resultSet==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getUserId(String userName) {
        Connection connection=getConnection();        {
            PreparedStatement  statement = null;
            try {
                statement = connection.prepareStatement("select * from users where user_name=?");
                statement.setString(1,userName);
                ResultSet resultSet= statement.executeQuery();
                while (resultSet.next()){
                    return resultSet.getString("reg_no");
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static Boolean returnBook(String bookName) {
        Connection connection=getConnection();
        try {
            PreparedStatement  statement = connection.prepareStatement("update books set available=?,issued_to=?,issued_date=? where title='"+bookName+"'");
            statement.setString(1,"Y");
            statement.setString(2,null);
            statement.setDate(3, null);
            int resultSet=statement.executeUpdate();
            connection.close();
            return resultSet==1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
