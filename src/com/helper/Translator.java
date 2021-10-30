package com.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {

  static Locale locale;
  static ResourceBundle rb;

  static {
    locale = new Locale("en", "US");
    rb = ResourceBundle.getBundle("\\com\\language\\language", locale);
  }

  public static Locale getLocale() {
    return locale;
  }

  public static void setLocale(Locale locale) {
    Translator.locale = locale;
    rb = ResourceBundle.getBundle("\\com\\language\\language", locale);
  }

  public static ResourceBundle getRb() {
    return rb;
  }

  public static String translate(String key) {
    return rb.getString(key);
  }

  public static void changeLanguagetoVietnamese() {
    setLocale(new Locale("vi", "VN"));
  }

  public static void changeLanguagetoEnglish() {
    setLocale(new Locale("en", "US"));
  }
}
