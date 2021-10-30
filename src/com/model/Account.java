package com.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    ObjectProperty<Integer> id;
    StringProperty email;
    StringProperty password;
    StringProperty type;
    StringProperty status;
    public static final String STATUS_OPEN = "Open";
    public static final String STATUS_LOCK = "Lock";

    public Account(Integer id, String email, String password, String type, String status){
        this.id = new SimpleObjectProperty<>(id);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
    }

    public Account() {

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

    public String getPassword() {
        return password.get();
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty getTypeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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
}
