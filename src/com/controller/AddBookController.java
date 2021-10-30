package com.controller;

import com.helper.BookDatabaseHelper;
import com.helper.Log;
import com.helper.Validation;
import com.view.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.annotation.Native;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtIdBook;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtName;

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.CANCEL_ADD_BOOK, AddBookController.class);
        Navigator.getInstance().goToLibraryHome();
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_ADD_BOOK, AddBookController.class);
        if (Validation.getInstance().validBookCode(txtIdBook.getText()) &&
            Validation.getInstance().validBookName(txtName.getText())) {
            boolean result = BookDatabaseHelper.insertBook(txtIdBook.getText(), txtName.getText(), Integer.parseInt(txtSoLuong.getText()));
            Log.getInstance().setLogINFOR(Log.ADD_BOOK_SUCCESS, AddBookController.class);
            Navigator.getInstance().goToLibraryHome();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.getInstance().setLogINFOR(Log.SHOW_ADD_BOOK, AddBookController.class);
    }
}
