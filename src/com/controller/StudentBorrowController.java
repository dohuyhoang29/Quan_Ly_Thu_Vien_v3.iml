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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class StudentBorrowController implements Initializable {
    @FXML
    private TableView<BorrowBook> tbBook;

    @FXML
    private TableColumn<BorrowBook, String> tcIdBook;

    @FXML
    private TableColumn<BorrowBook, String> tcNameBook;

    @FXML
    private TableColumn<BorrowBook, String> tcBorrowDate;

    @FXML
    private TableColumn<BorrowBook, Integer> tcQuantity;

    @FXML
    private TableColumn<BorrowBook, String> tcStatus;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnGoBack;

    @FXML
    private Label lbCurrentDate;

    @FXML
    void clickGoBack(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.GO_BACK, StudentBorrowController.class);
        Navigator.getInstance().goToStudentHome();
    }

    @FXML
    void clickReturn(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.RETURN_BOOK, StudentBorrowController.class);
        BorrowBook borrow = tbBook.getSelectionModel().getSelectedItem();
        if(borrow == null){
            Log.getInstance().setLogERROR(Log.NOT_CHOOSE_BOOK, StudentBorrowController.class);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Khong co sach nao duoc chon!");
            alert.show();
        } else {
            int lateDay = Period.between(borrow.getDueDate(), LibraryManager.getInstance().getCurrentDate()).getDays();
            System.out.println(lateDay);
            String status = null;
            int price = 0;
            if(lateDay > 0){
                status = returnLate;
                if(lateDay < 3){
                    price = 10000;
                }
                if(lateDay >= 3 && lateDay < 7){
                    price = 20000;
                }
                if(lateDay >= 7){
                    price = 20000 + ((lateDay - 7) * 3000);
                }
            }
            if(lateDay <= 0){
                status = returnIntime;
                price = 0;
            }
            BorrowBookDatabaseHelper.returnBook(borrow.getId(), status, borrow.getQuantity() - 1, price);
            Navigator.getInstance().goToStudentBorrow();
        }
    }

    static final String returnLate = "RETURN LATE";
    static final String returnIntime = "RETURN INTIME";
    ObservableList<BorrowBook> listBorrow = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<BorrowBook> list = BorrowBookDatabaseHelper.getAllBorrowBookByEmail(LibraryManager.getInstance().getAccount().getEmail());
        listBorrow.addAll(list);
        tbBook.setItems(listBorrow);
        tcIdBook.setCellValueFactory(cell -> cell.getValue().getCodeProperty());
        tcNameBook.setCellValueFactory(cell -> cell.getValue().getBookNameProperty());
        tcBorrowDate.setCellValueFactory(cell -> cell.getValue().getBorrowDatePropertyProperty());
        tcQuantity.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
        tcStatus.setCellValueFactory(cell -> cell.getValue().getStatusProperty());

        Log.getInstance().setLogINFOR(Log.SHOW_STUDENT_BORROW, StudentBorrowController.class);
    }
}
