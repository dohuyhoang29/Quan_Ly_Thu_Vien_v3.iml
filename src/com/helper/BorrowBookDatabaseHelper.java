package com.helper;

import com.model.Account;
import com.model.Book;
import com.model.BorrowBook;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookDatabaseHelper {

  public static boolean insertBorrowBook(Book book, Account account) {
    String query = "INSERT INTO borrow_book (`code`, name_book, email, `quantity`, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, book.getCodeBook());
      preStm.setString(2, book.getName());
      preStm.setString(3, account.getEmail());
      preStm.setInt(4, 1);
      preStm.setDate(5, Date.valueOf(LibraryManager.getInstance().getCurrentDate()));
      System.out.println(Date.valueOf(LibraryManager.getInstance().getCurrentDate()));
      preStm.setDate(6, Date.valueOf(LibraryManager.getInstance().getCurrentDate().plusDays(7)));
      preStm.setString(7, "NOT RETURN");
      if (preStm.executeUpdate() > 0) {
        return true;
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return false;
  }

  public static boolean addBorrowBook(Integer id, Integer quantity) {
    String query = "UPDATE borrow_book SET quantity = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, quantity);
      preStm.setInt(2, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  public static List<BorrowBook> getAllBorrowBook() {
    List<BorrowBook> listBorrow = new ArrayList<>();
    String query = "SELECT * FROM borrow_book;";
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query);
        ResultSet rs = preStm.executeQuery()) {
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String code = rs.getString("code");
        String email = rs.getString("email");
        String bookName = rs.getString("name_book");
        Integer quantity = rs.getInt("quantity");
        LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
        LocalDate dueDate = rs.getDate("due_date").toLocalDate();
        LocalDate returnDate = null;
        if (rs.getDate("return_date") != null) {
          returnDate = rs.getDate("return_date").toLocalDate();
        }
        String status = rs.getString("status");
        Integer bill = rs.getInt("bill");
        listBorrow.add(
            new BorrowBook(id, code, email, bookName, quantity, borrowDate, dueDate, returnDate,
                status, bill));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }

    return listBorrow;
  }

  public static List<BorrowBook> getAllBorrowBookByEmail(String email) {
    List<BorrowBook> listBorrow = new ArrayList<>();
    String query = "SELECT * FROM borrow_book WHERE email = ?;";
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setString(1, email);
      ResultSet rs = preStm.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String code = rs.getString("code");
        String Email = rs.getString("email");
        String bookName = rs.getString("name_book");
        Integer quantity = rs.getInt("quantity");
        LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
        LocalDate dueDate = rs.getDate("due_date").toLocalDate();
        LocalDate returnDate = null;
        if (rs.getDate("return_date") != null) {
          returnDate = rs.getDate("return_date").toLocalDate();
        }
        String status = rs.getString("status");
        Integer bill = rs.getInt("bill");
        listBorrow.add(
            new BorrowBook(id, code, Email, bookName, quantity, borrowDate, dueDate, returnDate,
                status, bill));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }

    return listBorrow;
  }

  public static List<BorrowBook> getAllBookWithCondition(LocalDate fromDate, LocalDate toDate,
      String status) {
    List<BorrowBook> listBorrow = new ArrayList<>();
    String query = "SELECT * FROM borrow_book WHERE 1 = 1";

    System.out.println(fromDate);
    System.out.println(toDate);
    System.out.println(status);
    if (fromDate != null && status.equalsIgnoreCase("ALL")) {
      query += " AND borrow_date >= ?";
    }
    if (toDate != null && status.equalsIgnoreCase("ALL")) {
      query += " AND borrow_date <= ?";
    }
    if (fromDate == null && toDate == null && !status.equalsIgnoreCase("ALL")) {
      query += " AND status = ?"
          + "";
    }
    if (status.equalsIgnoreCase("ALL") && fromDate == null && toDate == null) {
      query += "";
    }

    if (fromDate != null && toDate != null && !status.equalsIgnoreCase("ALL")) {
      query += " AND status = ? AND borrow_date <= ? AND borrow_date >= ?";
    }
    System.out.println(query);
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      if (fromDate == null && toDate == null && !status.equalsIgnoreCase("ALL")) {
        preStm.setString(1, status);
      }
      if (fromDate != null && status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(fromDate));
      }
      if (toDate != null && status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(toDate));
      }
      if (fromDate != null && toDate != null && status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(fromDate));
        preStm.setDate(2, Date.valueOf(toDate));
      }
      if (fromDate != null && toDate == null && !status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(fromDate));
        preStm.setString(2, status);
      }
      if (toDate != null && toDate == null && !status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(toDate));
        preStm.setString(2, status);
      }
      if (fromDate != null && toDate != null && !status.equalsIgnoreCase("ALL")) {
        preStm.setDate(1, Date.valueOf(fromDate));
        preStm.setDate(2, Date.valueOf(toDate));
        preStm.setString(3, status);
      }
      ResultSet rs = preStm.executeQuery();
      while (rs.next()) {
        Integer id = rs.getInt("id");
        String code = rs.getString("code");
        String email = rs.getString("email");
        String bookName = rs.getString("name_book");
        Integer quantity = rs.getInt("quantity");
        LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
        LocalDate dueDate = rs.getDate("due_date").toLocalDate();
        LocalDate returnDate = null;
        if (rs.getDate("return_date") != null) {
          returnDate = rs.getDate("return_date").toLocalDate();
        }
        String Status = rs.getString("status");
        Integer bill = rs.getInt("bill");
        listBorrow.add(
            new BorrowBook(id, code, email, bookName, quantity, borrowDate, dueDate, returnDate,
                Status, bill));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }

    return listBorrow;
  }

  public static boolean returnBook(Integer id, String status, Integer quantity, int price) {
    String query = "UPDATE borrow_book SET quantity = ?, status = ?, return_date = ?, bill = ? WHERE id = ?";
    try (Connection cnt = DatabaseHelper.getConnection();
        PreparedStatement preStm = cnt.prepareStatement(query)) {
      preStm.setInt(1, quantity);
      preStm.setString(2, status);
      preStm.setDate(3, Date.valueOf(LibraryManager.getInstance().getCurrentDate()));
      preStm.setInt(4, price);
      preStm.setInt(5, id);
      if (preStm.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }
}
