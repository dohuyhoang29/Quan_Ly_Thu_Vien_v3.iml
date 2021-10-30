package com.helper;

import com.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Log {
    private static Log log;

    //Login
    public final static String SHOW_LOGIN = "Show login screen";
    public final static String SUBMIT_LOGIN = "Click submit button to login";
    public final static String ACC_NOT_EXITS = "Account not exits";
    public final static String LOGIN_SUCCESS = "Login Success";
    public final static String PASS_INCORRECT = "Password incorrect";

    //AdminHome
    public final static String SHOW_ADMIN_HOME = "Show Admin Home screen";
    public final static String SHOW_DELETE_CONFIRM = "Show delete screen";
    public final static String ADD_ACCOUNT = "Click add new account button";
    public final static String CHANGE_PASS = "Click change password button";
    public final static String DELETE = "Click delete button";
    public final static String EDIT = "Click edit button";
    public final static String LOCK = "Click lock button";
    public final static String LOGOUT = "Click logout button";
    public final static String RESET = "Click reset button";
    public final static String UN_LOCK = "Click un-lock button";
    public final static String DELETE_OK = "Click OK on delete screen";
    public final static String DELETE_CANCEL = "Click Cancel on delete screen";
    public final static String LANGUAGE_VIETNAM = "Click change language to vietnamese";
    public final static String LANGUAGE_ENGLISH = "Click change language to english";
    public final static String DELETE_SUCCESS = "Delete success";
    public final static String LOCK_SUCCESS = "Lock success";
    public final static String UN_LOCK_SUCCESS = "Un-lock success";
    public final static String CHOOSE_ACC = "Choose a account";
    public final static String NOT_CHOOSE_ACC = "No account is selected";

    //AddAccount
    public final static String SHOW_ADD_ACCOUNT = "Show Add account screen";
    public final static String CANCEL_ADD_ACCOUNT = "Click cancel on add account screen";
    public final static String SUBMIT_ADD_ACCOUNT = "Click submit button to add account";
    public final static String ADD_ACC_SUCCESS = "Add account success";

    //AddBook
    public final static String SHOW_ADD_BOOK = "Show Add book screen";
    public final static String CANCEL_ADD_BOOK = "Click cancel on add book screen";
    public final static String SUBMIT_ADD_BOOK = "Click submit button to add book";
    public final static String ADD_BOOK_SUCCESS = "Add book success";

    //BorrowBook
    public final static String SHOW_BORROW_BOOK = "Show borrow book screen";
    public final static String GO_BACK = "Click go back library home screen";
    public final static String APPLY = "Click apply on borrow book screen";

    //ChangeDate
    public final static String SHOW_CHANGE_DATE = "Show change date screen";
    public final static String CANCEL_CHANGE_DATE = "Click cancel on change date screen";
    public final static String SUBMIT_CHANGE_DATE = "Click submit button to change date";

    //ChangePass
    public final static String SHOW_CHANGE_PASS = "Show change pass screen";
    public final static String CANCEL_CHANGE_PASS = "Click cancel on change pass screen";
    public final static String SUBMIT_CHANGE_PASS = "Click submit button to pass date";
    public final static String CHANGE_PASS_SUCCESS = "Add book success";
    public final static String PASS_DIDNT_MATCH = "Those passwords didn't match";
    public final static String WRONG_PASS = "Wrong pass";

    //EditAccount
    public final static String SHOW_EDIT_ACC = "Show edit account screen";
    public final static String CANCEL_EDIT_ACC = "Click cancel on edit account screen";
    public final static String SUBMIT_EDIT_ACC = "Click submit button to edit account";
    public final static String EDIT_ACC_SUCCESS = "Edit account success";

    //LibraryHome
    public final static String ADD_BOOK = "Click add new book button";
    public final static String LIST_BORROW = "Click list borrow book button";
    public final static String UPDATE_DATE = "Click update date button";

    //ResetPassword
    public final static String SHOW_RESET_PASS = "Show reset password screen";
    public final static String CANCEL_RESET_PASS = "Click cancel on reset password screen";
    public final static String SUBMIT_RESET_PASS = "Click submit button to reset password";
    public final static String RESET_PASS_SUCCESS = "Reset password success";

    //StudentBorrowBook
    public final static String SHOW_STUDENT_BORROW = "Show student borrow book screen";
    public final static String RETURN_BOOK = "Click return button to return book";
    public final static String NOT_CHOOSE_BOOK = "No book is selected";

    //StudentHome
    public final static String SHOW_STUDENT_HOME = "Show student home screen";
    public final static String BORROW_BOOK = "Click borrow book button";
    public final static String MY_BORROW_BOOK = "Click my borrow book button";

    public static Log getInstance(){
        if(log == null){
            log = new Log();
        }

        return log;
    }

    public void setLogERROR(String message, Class<?> clazz){
        Logger logger = LogManager.getLogger(clazz);
        logger.error(message);
    }

    public void setLogINFOR(String message, Class<?> clazz){
        Logger logger = LogManager.getLogger(clazz);
        logger.info(message);
    }
}
