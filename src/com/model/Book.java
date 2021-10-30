package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    //1. Properties
    ObjectProperty<Integer> id;
    StringProperty codeBook;
    StringProperty name;
    ObjectProperty<Integer> quantity;
    ObjectProperty<Integer> availabel;
    ObjectProperty<Integer> borrowed;

    //2. Constructer
    public Book(){}

    public Book(Integer id, String idBook, String name, Integer quantity, Integer borrowed){
        this.id = new SimpleObjectProperty<>(id);
        this.codeBook = new SimpleStringProperty(idBook);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleObjectProperty<>(quantity);
        this.availabel = new SimpleObjectProperty<>(quantity - borrowed);
        this.borrowed = new SimpleObjectProperty<>(borrowed);
    }

    //3. Getter and Setter


    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> getIdProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getCodeBook() {
        return codeBook.get();
    }

    public StringProperty getCodeBookProperty() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook.set(codeBook);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public Integer getAvailabel() {
        return availabel.get();
    }

    public ObjectProperty<Integer> getAvailabelProperty() {
        return availabel;
    }

    public void setAvailabel(Integer availabel) {
        this.availabel.set(availabel);
    }

    public Integer getBorrowed() {
        return borrowed.get();
    }

    public ObjectProperty<Integer> getBorrowedProperty() {
        return borrowed;
    }

    public void setBorrowed(Integer borrowed) {
        this.borrowed.set(borrowed);
        int newAvailable = this.quantity.get() - this.borrowed.get();
        this.availabel.set(newAvailable);
    }
}
