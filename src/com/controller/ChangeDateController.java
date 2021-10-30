package com.controller;

import com.helper.LibraryManager;
import com.helper.Log;
import com.view.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeDateController implements Initializable {
    @FXML
    private DatePicker dtpChangeDate;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    public ChangeDateController() {
    }

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLibraryHome();
        Log.getInstance().setLogINFOR(Log.CANCEL_CHANGE_DATE, ChangeDateController.class);//setLog
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_CHANGE_DATE, ChangeDateController.class);//setLog
        LibraryManager.getInstance().setCurrentDate(dtpChangeDate.getValue());
        Navigator.getInstance().goToLibraryHome();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dtpChangeDate.setValue(LibraryManager.getInstance().getCurrentDate());

        Log.getInstance().setLogINFOR(Log.SHOW_CHANGE_DATE, ChangeDateController.class);//setLog
    }
}
