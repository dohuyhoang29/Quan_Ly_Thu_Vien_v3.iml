package com.helper;

import com.model.Account;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseHelper {
    public static Account getAccountByEmail(String email){
        String query = "SELECT * FROM account WHERE email = ?";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query)){
            preStm.setString(1, email);
            ResultSet rs = preStm.executeQuery();
            if(rs.next()){
                Integer id = rs.getInt("id");
                String Email = rs.getString("email");
                String pass = rs.getString("password");
                String type = rs.getString("type");
                String status = rs.getString("status");
                return new Account(id, Email, pass, type, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static List<Account> getAllAccount(){
        List<Account> listAcc = new ArrayList<Account>();
        String query = "SELECT * FROM account";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);
            ResultSet rs = preStm.executeQuery();){
            while (rs.next()){
                Integer id = rs.getInt("id");
                String Email = rs.getString("email");
                String pass = rs.getString("password");
                String type = rs.getString("type");
                String status = rs.getString("status");
                listAcc.add(new Account(id, Email, pass, type, status));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }

        return listAcc;
    }

    public static boolean insertAccount(String email, String pass, String type){
        String query = "INSERT INTO account (email, `password`, `type`, `status`) VALUES (?, ?, ?, ?);";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);){
            preStm.setString(1, email);
            preStm.setString(2, pass);
            preStm.setString(3, type);
            preStm.setString(4, Account.STATUS_OPEN);
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

    public static boolean editAccount(String email, String pass, String type, Integer id) throws SQLException {
        String query = "UPDATE account SET email = ? , `password` = ?, `type` = ? WHERE id = ?;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query)){
            preStm.setString(1, email);
            preStm.setString(2, pass);
            preStm.setString(3, type);
            preStm.setInt(4, id);
            if(preStm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean deleteAccount(Integer id){
        String query = "DELETE FROM account WHERE id = ?;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);){
            preStm.setInt(1, id);
            if(preStm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean lockAccount(Integer id){
        String query = "UPDATE account SET status = 'Lock' WHERE id = ?;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);) {
            preStm.setInt(1, id);
            if(preStm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean unLockAccount(Integer id){
        String query = "UPDATE account SET status = 'Open' WHERE id = ?;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);) {
            preStm.setInt(1, id);
            if(preStm.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean changePassword(String pass, Integer id){
        String query = "UPDATE account SET password = ? WHERE id = ?;";
        try(Connection cnt = DatabaseHelper.getConnection();
            PreparedStatement preStm = cnt.prepareStatement(query);) {
            preStm.setString(1, pass);
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
