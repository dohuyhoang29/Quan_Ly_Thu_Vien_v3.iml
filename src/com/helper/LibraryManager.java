package com.helper;

import com.model.Account;

import java.time.LocalDate;

public class LibraryManager extends Account{
//    private AccountManager(){}
    private static LibraryManager libraryManager;
    private static LocalDate currentDate;
    public Account account = new Account();

    public static LibraryManager getInstance(){
     if(libraryManager == null){
         libraryManager = new LibraryManager();
         currentDate = LocalDate.now();
     }
     return libraryManager;
    }

    public void saveAccount(Account account){
        this.account = account;
    }

    public Account getAccount(){
        return this.account;
    }

    public static LibraryManager getLibraryManager() {
        return libraryManager;
    }

    public static void setLibraryManager(LibraryManager libraryManager) {
        LibraryManager.libraryManager = libraryManager;
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        LibraryManager.currentDate = currentDate;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
