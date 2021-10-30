package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.Log;
import com.helper.Validation;
import com.model.Account;
import com.view.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddAccountController implements Initializable {
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPass;

    @FXML
    private ChoiceBox<String> cbType;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    public AddAccountController() {
    }

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminMenu();
        Log.getInstance().setLogINFOR(Log.CANCEL_ADD_ACCOUNT, AddAccountController.class);
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_ADD_ACCOUNT, AddAccountController.class);
        if(Validation.getInstance().validEmail(txtEmail.getText()) &&
                Validation.getInstance().validRequired(txtPass.getText()) &&
                Validation.getInstance().validRequired(cbType.getValue()) &&
                Validation.getInstance().validPassword(txtPass.getText())){
            boolean result = AccountDatabaseHelper.insertAccount(txtEmail.getText(), txtPass.getText(), cbType.getValue());
            Log.getInstance().setLogINFOR(Log.ADD_ACC_SUCCESS, AddAccountController.class);
            if(result){
                Navigator.getInstance().goToAdminMenu();
            } else {
                Navigator.getInstance().goToAdminMenu();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbType.getItems().add("Admin");
        cbType.getItems().add("Library");
        cbType.getItems().add("Student");

        Log.getInstance().setLogINFOR(Log.SHOW_ADD_ACCOUNT, AddAccountController.class);
    }


}
