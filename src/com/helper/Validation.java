package com.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

public class Validation {

  private Validation() {
  }

  private static Validation validation;

  public static Validation getInstance() {
    if (validation == null) {
      validation = new Validation();
    }
    return validation;
  }

  static Pattern pattern;
  static Matcher matcher;
  static String regex;

  public boolean validEmail(String email) {
    regex = "^[A-z0-9_.]{1,}@[A-z0-9_.]{1,}\\.[A-z0-9]{1,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(email);
    if (!matcher.find()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter the correct format Email");
      alert.show();
      return false;
    }
    return true;
  }

  public boolean validRequired(String text) {
    if (text == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Do not leave blank");
      alert.show();
      return false;
    }
    return true;
  }

  public boolean validPassword(String password) {
//        regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    regex = "^(?=.*[A-Z]).{8,20}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(password);

    if (!matcher.find()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter the correct format Password");
      alert.show();
      return false;
    }
    return true;
  }

  public boolean validBookCode(String code) {
    regex = "^[A-Z][A-Z][0-9]{6}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(code);

    if (!matcher.find()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter the correct format Book Code");
      alert.show();
      return false;
    }
    return true;
  }

  public boolean validBookName(String name) {
    regex = "^[A-Z0-9][A-z0-9 ]{0,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(name);

    if (!matcher.find()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter the correct format Book Name");
      alert.show();
      return false;
    }
    return true;
  }

  public boolean validBookAuthor(String author) {
    regex = "^[A-Z0-9][A-Z0-9 ]{0,}$";
    pattern = Pattern.compile(regex);

    matcher = pattern.matcher(author);

    if (!matcher.find()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter the correct format Book Author");
      alert.show();
      return false;
    }
    return true;
  }
}
