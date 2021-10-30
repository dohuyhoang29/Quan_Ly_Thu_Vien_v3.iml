package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class BorrowBook {
    ObjectProperty<Integer> id;
    StringProperty code;
    StringProperty email;
    StringProperty bookName;
    ObjectProperty<Integer> quantity;
    LocalDate borrowDate;
    LocalDate dueDate;
    LocalDate returnDate;
    StringProperty borrowDateProperty;
    StringProperty dueDateProperty;
    StringProperty returnDateProperty;
    StringProperty status;
    ObjectProperty<Integer> bill;

    public BorrowBook(Integer id, String code, String email, String bookName, Integer quantity, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, String status, Integer bill){
        this.id = new SimpleObjectProperty<>(id);
        this.code = new SimpleStringProperty(code);
        this.email = new SimpleStringProperty(email);
        this.bookName = new SimpleStringProperty(bookName);
        this.quantity = new SimpleObjectProperty<>(quantity);
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = new SimpleStringProperty(status);
        this.bill = new SimpleObjectProperty<>(bill);
        this.borrowDateProperty = new SimpleStringProperty(borrowDate.toString());
        this.dueDateProperty = new SimpleStringProperty(dueDate.toString());
        if(returnDate == null){
            this.returnDateProperty = new SimpleStringProperty("UNKNOW");
        } else {
            this.returnDateProperty = new SimpleStringProperty(returnDate.toString());
        }
    }

    public BorrowBook(){}

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty getCodeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty getEmailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty getBookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public ObjectProperty<Integer> getQuantityProperty() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        this.borrowDateProperty.set(borrowDate.toString());
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowDateProperty() {
        return borrowDateProperty.get();
    }

    public StringProperty getBorrowDatePropertyProperty() {
        return borrowDateProperty;
    }

    public void setBorrowDateProperty(String borrowDateProperty) {
        this.borrowDateProperty.set(borrowDateProperty);
    }

    public String getDueDateProperty() {
        return dueDateProperty.get();
    }

    public StringProperty getDueDatePropertyProperty() {
        return dueDateProperty;
    }

    public void setDueDateProperty(String dueDateProperty) {
        this.dueDateProperty.set(dueDateProperty);
    }

    public String getReturnDateProperty() {
        return returnDateProperty.get();
    }

    public StringProperty getReturnDatePropertyProperty() {
        return returnDateProperty;
    }

    public void setReturnDateProperty(String returnDateProperty) {
        this.returnDateProperty.set(returnDateProperty);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty getStatusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Integer getBill() {
        return bill.get();
    }

    public ObjectProperty<Integer> getBillProperty() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill.set(bill);
    }
}
