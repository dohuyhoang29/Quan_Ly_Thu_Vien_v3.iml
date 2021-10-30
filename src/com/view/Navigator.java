package com.view;

import com.controller.EditAccountController;
import com.controller.ResetPasswordController;
import com.helper.Translator;
import com.model.Account;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {

  private Navigator() {
  }
    
  static final String ADMIN_HOME = "AdminHomeUI.fxml";
  static final String LOGIN = "LoginUI.fxml";
  static final String ADD_ACCOUNT = "AddAccountUI.fxml";
  static final String EDIT_ACCOUNT = "EditAccountUI.fxml";
  static final String RESET_PASS = "ResetPasswordUI.fxml";
  static final String CHANGE_PASS = "ChangePassUI.fxml";
  static final String LIBRARY_HOME = "LibraryHomeUI.fxml";
  static final String ADD_BOOK = "AddBookUI.fxml";
  static final String CHANGE_DATE = "ChangeDateUI.fxml";
  static final String STUDENT_HOME = "StudentHomeUI.fxml";
  static final String BORROW_BOOK = "BorrowBookUI.fxml";
  static final String STUDENT_BORROW = "StudentBorrowUI.fxml";

  private FXMLLoader loader;
  private static Navigator navigator;

  private Stage stage;

  public static Navigator getInstance() {
    if (navigator == null) {
      navigator = new Navigator();
    }
    return navigator;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void goToScene(String title, String url) throws IOException {
    loader = new FXMLLoader(getClass().getResource(url), Translator.getRb());
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle(title);
    if (!stage.isShowing()) {
      stage.show();
    }
  }

  public void goToAdminHome() throws IOException {
    goToScene("AdminHome", ADMIN_HOME);
  }

  public void goToLogin() throws IOException {
    goToScene("Login", LOGIN);
  }

  public void goToAddAccount() throws IOException {
    goToScene("AddAccount", ADD_ACCOUNT);
  }

  public void goToEditAccount(Account account) throws IOException {
    goToScene("EditAccount", EDIT_ACCOUNT);
    EditAccountController controller = loader.getController();
    controller.loadData(account);
  }

  public void goToResetPass(Account account) throws IOException {
    goToScene("Reset Password", RESET_PASS);
    ResetPasswordController controller = loader.getController();
    controller.loadData(account);
  }

  public void goToAdminMenu() throws IOException {
    goToScene("Admin Menu", ADMIN_HOME);
  }

  public void goToChangePass() throws IOException {
    goToScene("Change Pass", CHANGE_PASS);
  }

  public void goToLibraryHome() throws IOException {
    goToScene("Library Menu", LIBRARY_HOME);
  }

  public void goToAddBook() throws IOException {
    goToScene("Add Book", ADD_BOOK);
  }

  public void goToChangeDate() throws IOException {
    goToScene("Change Date", CHANGE_DATE);
  }

  public void goToStudentHome() throws IOException {
    goToScene("Student Home", STUDENT_HOME);
  }

  public void goToBorrowBook() throws IOException {
    goToScene("Borrow Book", BORROW_BOOK);
  }

  public void goToStudentBorrow() throws IOException {
    goToScene("Student Borrow", STUDENT_BORROW);
  }
}
