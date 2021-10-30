package com.controller;

import com.helper.BookDatabaseHelper;
import com.helper.LibraryManager;
import com.helper.Log;
import com.helper.Translator;
import com.model.Book;
import com.view.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LibraryHomeController implements Initializable {
    @FXML
    private Label lbCurrentDate;

    @FXML
    private TableView<Book> tbBook;

    @FXML
    private TableColumn<Book, String> tcIdBook;

    @FXML
    private TableColumn<Book, String> tcName;

    @FXML
    private TableColumn<Book, Integer> tcQuantity;

    @FXML
    private TableColumn<Book, Integer> tcAvailable;

    @FXML
    private TableColumn<Book, Integer> tcBorrowed;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnUpdateDate;

    @FXML
    private Button btnListBookBorrowed;

    @FXML
    private Button btnChangePass;

    @FXML
    private Button btnLogout;

    @FXML
    private MenuButton mbLanguage;

    @FXML
    void clickAddBook(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.ADD_BOOK, LibraryHomeController.class);
        Navigator.getInstance().goToAddBook();
    }

    @FXML
    void clickChangePass(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.CHANGE_PASS, LibraryHomeController.class);
        Navigator.getInstance().goToChangePass();
    }

    @FXML
    void clickListBookBorrowed(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.LIST_BORROW, LibraryHomeController.class);
        Navigator.getInstance().goToBorrowBook();
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.LOGOUT, LibraryHomeController.class);
        Navigator.getInstance().goToLogin();
    }

    @FXML
    void clickUpdateDate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToChangeDate();
        Log.getInstance().setLogINFOR(Log.UPDATE_DATE, LibraryHomeController.class);
    }

    ObservableList<Book> listBook = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> data = BookDatabaseHelper.getAllBook();
        listBook.addAll(data);
        tbBook.setItems(listBook);
        tcIdBook.setCellValueFactory(cellData -> cellData.getValue().getCodeBookProperty());
        tcName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        tcAvailable.setCellValueFactory(cellData -> cellData.getValue().getAvailabelProperty());
        tcBorrowed.setCellValueFactory(cellData -> cellData.getValue().getBorrowedProperty());
        tcQuantity.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty());
        lbCurrentDate.setText(LibraryManager.getInstance().getCurrentDate().toString());

        MenuItem vietnam = new MenuItem("Viet Nam");
        MenuItem english = new MenuItem("English");
        mbLanguage.getItems().addAll(vietnam, english);

        vietnam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Translator.changeLanguagetoVietnamese();
                Log.getInstance().setLogINFOR(Log.LANGUAGE_VIETNAM, LibraryHomeController.class);
                try {
                    Navigator.getInstance().goToLibraryHome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        english.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Translator.changeLanguagetoEnglish();
                Log.getInstance().setLogINFOR(Log.LANGUAGE_ENGLISH, LibraryHomeController.class);
                try {
                    Navigator.getInstance().goToLibraryHome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
