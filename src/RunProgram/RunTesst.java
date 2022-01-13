package RunProgram;

import Admin.AccountAdmin;
import Admin.PageManagerOfProvince;
import Client.PageManagerOfClient;
import FileControll.FileController;
import PersonManagerApp.CMND;
import PersonManagerApp.DatePublic;
import UserAccount.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class RunTesst {
    static PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
    static FileController fileController = new FileController();
    static Scanner sc = new Scanner(System.in);
    static CMND cmnd = new CMND();
    static AccountAdmin accountAdmin = new AccountAdmin();
    static PageManagerOfClient pageManagerOfClient = new PageManagerOfClient();
    public static void main(String[] args) {
        usingThreadLocalClass();
    }
        static void usingThreadLocalClass() {
            pageManagerOfClient.forgotPassworkOfClient(118561518979l);
    }
}