package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.Log;
import com.helper.Validation;
import com.model.Account;
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

public class ResetPasswordController implements Initializable {
    @FXML
    private TextField txtNewPass;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtConfirmPass;

    public ResetPasswordController() {
    }

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminHome();
        Log.getInstance().setLogINFOR(Log.CANCEL_RESET_PASS, ResetPasswordController.class);
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_RESET_PASS, ResetPasswordController.class);
        if(txtNewPass.getText().equalsIgnoreCase(txtConfirmPass.getText())){
            if(Validation.getInstance().validRequired(txtConfirmPass.getText()) && Validation.getInstance().validRequired(txtNewPass.getText())){
                boolean result = AccountDatabaseHelper.changePassword(txtNewPass.getText(), acc.getId());
                Log.getInstance().setLogINFOR(Log.RESET_PASS_SUCCESS, ResetPasswordController.class);
                Navigator.getInstance().goToAdminHome();
            }
        } else {
            Log.getInstance().setLogINFOR(Log.PASS_DIDNT_MATCH, ResetPasswordController.class);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Those passwords didn't match!");
            alert.show();
        }
    }

    Account acc = new Account();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.getInstance().setLogINFOR(Log.SHOW_RESET_PASS, ResetPasswordController.class);
    }

    public void loadData(Account account){
        this.acc = account;
    }
}
