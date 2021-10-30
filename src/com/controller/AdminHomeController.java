package com.controller;

import com.helper.AccountDatabaseHelper;
import com.helper.Log;
import com.helper.Translator;
import com.model.Account;
import com.view.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminHomeController implements Initializable {

  @FXML
  private TableView<Account> tbAccount;

  @FXML
  private TableColumn<Account, String> tcEmail;

  @FXML
  private TableColumn<Account, String> tcType;

  @FXML
  private TableColumn<Account, String> tcStatus;

  @FXML
  private Button btnAdd;

  @FXML
  private Button btnChangePass;

  @FXML
  private Button btnEdit;

  @FXML
  private Button btnLock;

  @FXML
  private Button btnUnLock;

  @FXML
  private Button btnReset;

  @FXML
  private Button btnLogout;

  @FXML
  private MenuButton mbLanguage;

  @FXML
  void clickAddAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.ADD_ACCOUNT, AdminHomeController.class);//setLog
    Navigator.getInstance().goToAddAccount();
  }

  @FXML
  void clickChangePass(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.CHANGE_PASS, AdminHomeController.class);//setLog
    Navigator.getInstance().goToChangePass();
  }

  @FXML
  void clickDeleteAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.DELETE, AdminHomeController.class);//setLog
    Account account = tbAccount.getSelectionModel().getSelectedItem();
    if (account != null) {
      Log.getInstance().setLogINFOR(Log.CHOOSE_ACC, AdminHomeController.class);//setLog

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Account");
      alert.setContentText("Ban co chac chan xoa tai khoan nay?");

      Log.getInstance().setLogINFOR(Log.SHOW_DELETE_CONFIRM, AdminHomeController.class);//setLog

      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK) {
        Log.getInstance().setLogINFOR(Log.DELETE_OK, AdminHomeController.class);//setLog
        boolean result = AccountDatabaseHelper.deleteAccount(account.getId());
        if (result) {
          Log.getInstance().setLogINFOR(Log.DELETE_SUCCESS, AdminHomeController.class);//setLog
          Navigator.getInstance().goToAdminHome();
        } else {
          Navigator.getInstance().goToAdminHome();
        }
      } else if (option.get() == ButtonType.CANCEL) {
        Log.getInstance().setLogINFOR(Log.DELETE_CANCEL, AdminHomeController.class);//setLog
        Navigator.getInstance().goToAdminHome();
      } else {
        Navigator.getInstance().goToAdminHome();
      }
    } else {
      Log.getInstance().setLogERROR(Log.NOT_CHOOSE_ACC, AdminHomeController.class);//setLog
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("No account is selected!");
      alert.show();
    }


  }

  @FXML
  void clickEditAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.EDIT, AdminHomeController.class);//setLog

    Account account = tbAccount.getSelectionModel().getSelectedItem();

    if (account != null) {
      Log.getInstance().setLogINFOR(Log.CHOOSE_ACC, AdminHomeController.class);//setLog
      Navigator.getInstance().goToEditAccount(account);
    } else {
      Log.getInstance().setLogERROR(Log.NOT_CHOOSE_ACC, AdminHomeController.class);//setLog
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("No account is selected!");
      alert.show();
    }
  }

  @FXML
  void clickLockAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.LOCK, AdminHomeController.class);//setLog
    Account account = tbAccount.getSelectionModel().getSelectedItem();
    if (account != null) {
      Log.getInstance().setLogINFOR(Log.CHOOSE_ACC, AdminHomeController.class);//setLog
      AccountDatabaseHelper.lockAccount(account.getId());
      Navigator.getInstance().goToAdminHome();
      Log.getInstance().setLogINFOR(Log.LOCK_SUCCESS, AdminHomeController.class);//setLog
    } else {
      Log.getInstance().setLogERROR(Log.NOT_CHOOSE_ACC, AdminHomeController.class);//setLog
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("No account is selected!");
      alert.show();
    }
  }

  @FXML
  void clickLogout(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.LOGOUT, AdminHomeController.class);//setLog
    Navigator.getInstance().goToLogin();
  }

  @FXML
  void clickResetAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.RESET, AdminHomeController.class);//setLog
    Account account = tbAccount.getSelectionModel().getSelectedItem();

    if (account != null) {
      Log.getInstance().setLogINFOR(Log.CHOOSE_ACC, AdminHomeController.class);//setLog
      Navigator.getInstance().goToResetPass(account);
    } else {
      Log.getInstance().setLogERROR(Log.NOT_CHOOSE_ACC, AdminHomeController.class);//setLog
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("No account is selected!");
      alert.show();
    }
  }

  @FXML
  void clickUnLockAccount(ActionEvent event) throws IOException {
    Log.getInstance().setLogINFOR(Log.UN_LOCK, AdminHomeController.class);//setLog
    Account account = tbAccount.getSelectionModel().getSelectedItem();
    if (account != null) {
      Log.getInstance().setLogINFOR(Log.CHOOSE_ACC, AdminHomeController.class);//setLog
      AccountDatabaseHelper.unLockAccount(account.getId());
      Log.getInstance().setLogINFOR(Log.UN_LOCK_SUCCESS, AdminHomeController.class);//setLog
      Navigator.getInstance().goToAdminHome();
    } else {
      Log.getInstance().setLogERROR(Log.NOT_CHOOSE_ACC, AdminHomeController.class);//setLog
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("No account is selected!");
      alert.show();
    }
  }

  ObservableList<Account> listAcc = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Log.getInstance().setLogINFOR(Log.SHOW_ADMIN_HOME, AdminHomeController.class);//setLog

    List<Account> listData = AccountDatabaseHelper.getAllAccount();
    listAcc.addAll(listData);
    tbAccount.setItems(listAcc);
    tcEmail.setCellValueFactory((cellData) -> cellData.getValue().getEmailProperty());
    tcType.setCellValueFactory((cellData) -> cellData.getValue().getTypeProperty());
    tcStatus.setCellValueFactory((cellData) -> cellData.getValue().getStatusProperty());

    MenuItem vietnam = new MenuItem("Viet Nam");
    MenuItem english = new MenuItem("English");
    mbLanguage.getItems().addAll(vietnam, english);

    vietnam.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Log.getInstance().setLogINFOR(Log.LANGUAGE_VIETNAM, AdminHomeController.class);//setLog
        Translator.changeLanguagetoVietnamese();
        try {
          Navigator.getInstance().goToAdminHome();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    english.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Log.getInstance().setLogINFOR(Log.LANGUAGE_ENGLISH, AdminHomeController.class);//setLog
        Translator.changeLanguagetoEnglish();
        try {
          Navigator.getInstance().goToAdminHome();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
