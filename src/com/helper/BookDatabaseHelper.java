package com.helper;

import com.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabaseHelper {
    public static List<Book> getAllBook(){
        List<Book> listBook = new ArrayList<Book>();
        String query = "SELECT * FROM book;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement stm = cnt.prepareStatement(query);
            ResultSet rs = stm.executeQuery();) {
            while(rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                Integer quantity = rs.getInt("quantity");
                Integer borrowed = rs.getInt("borrowed");
                listBook.add(new Book(id, code, name, quantity, borrowed));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return listBook;
    }

    public static boolean insertBook(String code, String name, int quantity){
        String query = "INSERT INTO book (code, `name`, `quantity`) VALUES (?, ?, ?);";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preStm.setString(1, code);
            preStm.setString(2, name);
            preStm.setInt(3, quantity);
            if(preStm.executeUpdate() > 0){
                ResultSet rs = preStm.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                }
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean changeQuantity(Integer id, Integer quantity){
        String query = "UPDATE book SET borrowed = ? WHERE id = ?";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);) {
            preStm.setInt(1, quantity);
            preStm.setInt(2, id);
            if(preStm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}
