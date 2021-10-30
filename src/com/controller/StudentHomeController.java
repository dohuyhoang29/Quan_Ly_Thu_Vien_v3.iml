package com.controller;

import com.helper.*;
import com.model.Book;
import com.model.BorrowBook;
import com.view.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {
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
    private Button btnBorrowBook;

    @FXML
    private Button btnMyBorrowedBook;

    @FXML
    private Button btnChangePass;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lbCurrentDate;

    @FXML
    private MenuButton mbLanguage;

    @FXML
    void clickBorrowBook(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.BORROW_BOOK, StudentHomeController.class);
        List<BorrowBook> listBorrow = BorrowBookDatabaseHelper.getAllBorrowBookByEmail(LibraryManager.getInstance().getAccount().getEmail());
        Book book = tbBook.getSelectionModel().getSelectedItem();
        if(book != null){
            if(book.getAvailabel() > 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure to borrow the book with name: " + book.getName());
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get() == ButtonType.OK){
                    for(int i = 0; i < listBorrow.size(); i++){
                        if(book.getName().equalsIgnoreCase(listBorrow.get(i).getBookName())){
//                            BorrowBookDatabaseHelper.addBorrowBook(listBorrow.get(i).getId(), listBorrow.get(i).getQuantity() + 1);
                            BookDatabaseHelper.changeQuantity(book.getId(), book.getBorrowed() + 1);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Borrow success");
                            alert.show();
                            Navigator.getInstance().goToStudentHome();
                            return;
                        }
                    }
                    if(BorrowBookDatabaseHelper.insertBorrowBook(book, LibraryManager.getInstance().getAccount())){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Borrow success");
                        alert.show();
                        book.setBorrowed(book.getBorrowed() + 1);
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This book not available now!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You need choose 1 book!");
            alert.show();
        }
    }

    @FXML
    void clickChangePass(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.CHANGE_PASS, StudentHomeController.class);
        Navigator.getInstance().goToChangePass();
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.LOGOUT, StudentHomeController.class);
        Navigator.getInstance().goToLogin();
    }

    @FXML
    void clickMyBorrowedBook(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.MY_BORROW_BOOK, StudentHomeController.class);
        Navigator.getInstance().goToStudentBorrow();
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

        Log.getInstance().setLogINFOR(Log.SHOW_STUDENT_HOME, StudentHomeController.class);

        MenuItem vietnam = new MenuItem("Viet Nam");
        MenuItem english = new MenuItem("English");
        mbLanguage.getItems().addAll(vietnam, english);

        vietnam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Log.getInstance().setLogINFOR(Log.LANGUAGE_VIETNAM, StudentHomeController.class);
                Translator.changeLanguagetoVietnamese();
                try {
                    Navigator.getInstance().goToStudentHome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        english.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Log.getInstance().setLogINFOR(Log.LANGUAGE_ENGLISH, StudentHomeController.class);
                Translator.changeLanguagetoEnglish();
                try {
                    Navigator.getInstance().goToStudentHome();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
