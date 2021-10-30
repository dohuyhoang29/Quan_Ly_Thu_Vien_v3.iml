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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAccountController implements Initializable {
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

    @FXML
    void clickCancel(ActionEvent event) throws IOException {
        Log.getInstance().setLogINFOR(Log.CANCEL_EDIT_ACC, EditAccountController.class);
        Navigator.getInstance().goToAdminHome();
    }

    @FXML
    void clickSubmit(ActionEvent event) throws IOException, SQLException {
        Log.getInstance().setLogINFOR(Log.SUBMIT_EDIT_ACC, EditAccountController.class);
        if (Validation.getInstance().validEmail(txtEmail.getText()) && Validation.getInstance().validRequired(txtPass.getText()) && Validation.getInstance().validRequired(cbType.getValue())) {
            boolean result = AccountDatabaseHelper.editAccount(txtEmail.getText(), txtPass.getText(), cbType.getValue(), account.getId());
            if (result) {
                Log.getInstance().setLogINFOR(Log.EDIT_ACC_SUCCESS, EditAccountController.class);
                Navigator.getInstance().goToAdminHome();
            } else {

            }
        }
    }

    Account account = new Account();
    Account acc = new Account();

    public String getEmail(Account account) {
        return account.getEmail();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbType.getItems().add("Admin");
        cbType.getItems().add("Library");
        cbType.getItems().add("Student");

        Log.getInstance().setLogINFOR(Log.SHOW_EDIT_ACC, EditAccountController.class);
    }

    public void loadData(Account account) {
        txtEmail.setText(account.getEmail());
        txtPass.setText(account.getPassword());
        cbType.setValue(account.getType());
        this.account = account;
    }

}
