package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.LibraryManager;
import com.helper.Log;
import com.view.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassController implements Initializable {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtCurrentPass;

    @FXML
    private TextField txtNewPass;

    @FXML
    private TextField txtConfirmPass;

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.CANCEL_CHANGE_PASS, ChangePassController.class);//setLog
        Navigator.getInstance().goToAdminMenu();
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_CHANGE_PASS, ChangePassController.class);
        if(LibraryManager.getInstance().getAccount().getPassword().equalsIgnoreCase(txtCurrentPass.getText())){
            if(txtNewPass.getText().equalsIgnoreCase(txtConfirmPass.getText())){
                Log.getInstance().setLogINFOR(Log.CHANGE_PASS_SUCCESS, ChangePassController.class);//setLog
                AccountDatabaseHelper.changePassword(txtNewPass.getText(), LibraryManager.getInstance().getAccount().getId());
                LibraryManager.getInstance().getAccount().setPassword(txtNewPass.getText());
                if(LibraryManager.getInstance().getAccount().getType().equalsIgnoreCase("Admin")){
                    Navigator.getInstance().goToAdminHome();
                }
                if (LibraryManager.getInstance().getAccount().getType().equalsIgnoreCase("Library")){
                    Navigator.getInstance().goToLibraryHome();
                }
                if (LibraryManager.getInstance().getAccount().getType().equalsIgnoreCase("Student")) {
                    Navigator.getInstance().goToStudentHome();
                }
            } else {
                Log.getInstance().setLogERROR(Log.PASS_DIDNT_MATCH, ChangePassController.class);//setLog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Those passwords didn't match!");
                alert.show();
            }
        } else {
            Log.getInstance().setLogERROR(Log.WRONG_PASS, ChangePassController.class);//setLog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Wrong password");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.getInstance().setLogINFOR(Log.SHOW_CHANGE_PASS, ChangePassController.class);//setLog
    }
}
