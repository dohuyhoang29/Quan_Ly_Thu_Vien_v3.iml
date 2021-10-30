package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.LibraryManager;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPass;

    @FXML
    void clickLogin(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_LOGIN, LoginController.class);
        Account account = AccountDatabaseHelper.getAccountByEmail(txtEmail.getText());
        if(Validation.getInstance().validRequired(txtEmail.getText()) && Validation.getInstance().validRequired(txtPass.getText())){
            if(account == null){
                Log.getInstance().setLogERROR(Log.ACC_NOT_EXITS, LoginController.class);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Account not exits");
                alert.setContentText("Email: " + txtEmail.getText() + " khong ton tai");
                alert.show();
            } else {
                if(txtPass.getText().equalsIgnoreCase(account.getPassword())){
                    Log.getInstance().setLogINFOR(Log.LOGIN_SUCCESS, LoginController.class);
                    if(account.getType().equalsIgnoreCase("Admin")){
                        Navigator.getInstance().goToAdminHome();
                    } else if (account.getType().equalsIgnoreCase("Library")){
                        Navigator.getInstance().goToLibraryHome();
                    } else {
                        Navigator.getInstance().goToStudentHome();
                    }
                } else {
                    Log.getInstance().setLogERROR(Log.PASS_INCORRECT, LoginController.class);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password incorrect");
                    alert.setContentText("Sai mat khau vui long nhap lai");
                    alert.show();
                }
            }
        }

        LibraryManager.getInstance().saveAccount(account);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Log.getInstance().setLogINFOR(Log.SHOW_LOGIN, LoginController.class);
    }
}
