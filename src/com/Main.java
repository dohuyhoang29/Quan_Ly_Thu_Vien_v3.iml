package com;

import com.view.Navigator;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
//
//        Locale locale = new Locale("en", "US");
//        Translator.setLocale(locale);
    System.out.println(Paths.get(".").toAbsolutePath());

    Navigator.getInstance().setStage(primaryStage);
    Navigator.getInstance().goToLogin();
  }
}
