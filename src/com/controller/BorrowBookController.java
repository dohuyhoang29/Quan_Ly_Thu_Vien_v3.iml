package com.controller;

import com.helper.BorrowBookDatabaseHelper;
import com.helper.LibraryManager;
import com.helper.Log;
import com.model.BorrowBook;
import com.view.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowBookController implements Initializable {
    @FXML
    private TableView<BorrowBook> tbBook;

    @FXML
    private TableColumn<BorrowBook, Integer> tcIdBook;

    @FXML
    private TableColumn<BorrowBook, String> tcNameBook;

    @FXML
    private TableColumn<BorrowBook, String> tcNameStudent;

    @FXML
    private TableColumn<BorrowBook, String> tcBorrowDate;

    @FXML
    private TableColumn<BorrowBook, String> tcDueDate;

    @FXML
    private TableColumn<BorrowBook, String> tcReturnDate;

    @FXML
    private TableColumn<BorrowBook, String> tcStatus;

    @FXML
    private TableColumn<BorrowBook, Integer> tcPrice;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnGoBack;

    @FXML
    private Label lbCurrentDate;

    @FXML
    private DatePicker dtpToDate;

    @FXML
    private DatePicker dtpFromDate;

    @FXML
    private ChoiceBox<String> cbStatus;

    @FXML
    void clickGoBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLibraryHome();
        Log.getInstance().setLogINFOR(Log.GO_BACK, BorrowBookController.class);//setLog
    }

    @FXML
    void clickApply(ActionEvent event) throws IOException {
        List<BorrowBook> listData = BorrowBookDatabaseHelper.getAllBookWithCondition(dtpFromDate.getValue(), dtpToDate.getValue(), cbStatus.getValue());
        System.out.println(listData.size());
        listBorrow.clear();
        listBorrow.addAll(listData);
        Log.getInstance().setLogINFOR(Log.APPLY, BorrowBookController.class);//setLog
    }

    ObservableList<BorrowBook> listBorrow = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<BorrowBook> data = BorrowBookDatabaseHelper.getAllBorrowBook();
        listBorrow.addAll(data);
        tbBook.setItems(listBorrow);
        tcIdBook.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        tcNameStudent.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        tcNameBook.setCellValueFactory(cellData -> cellData.getValue().getBookNameProperty());
        tcBorrowDate.setCellValueFactory(cellData -> cellData.getValue().getBorrowDatePropertyProperty());
        tcDueDate.setCellValueFactory(cellData -> cellData.getValue().getDueDatePropertyProperty());
        tcReturnDate.setCellValueFactory(cellData -> cellData.getValue().getReturnDatePropertyProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        tcPrice.setCellValueFactory(cellData -> cellData.getValue().getBillProperty());

        lbCurrentDate.setText(LibraryManager.getInstance().getCurrentDate().toString());

        cbStatus.setValue("ALL");
        cbStatus.getItems().add("ALL");
        cbStatus.getItems().add("NOT RETURNED");
        cbStatus.getItems().add("LATE");
        cbStatus.getItems().add("RETURNED INTIME");
        cbStatus.getItems().add("RETURNED LATE");

        Log.getInstance().setLogINFOR(Log.SHOW_BORROW_BOOK, BorrowBookController.class);//setLog
    }
}
