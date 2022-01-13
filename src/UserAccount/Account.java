package UserAccount;

import Admin.PageManagerOfProvince;
import FileControll.FileController;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    Scanner sc = new Scanner(System.in);
    Pattern patternUserName = Pattern.compile("^[A-Za-z_]\\w{7,29}$");
    Pattern patternPasswork = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$");
    private long idAccount;
    private String username;
    private String passwork;
    FileController fileController = new FileController();
    public void input(){
        PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
        do{
            System.out.print("Enter UserName :");username = sc.nextLine();
        }while(!patternUserName.matcher(getUsername()).find());
        do{
            System.out.print("Enter Passwork :"); passwork = sc.nextLine();
        }while(!patternPasswork.matcher(getPasswork()).find());
    }

    public void input(String filename){
        PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
        do{
            System.out.print("Enter UserName :");username = sc.nextLine();
        }while(!patternUserName.matcher(getUsername()).find() || pageManagerOfProvince.checkUsernameAccountxist(filename,username));
        do{
            System.out.print("Enter Passwork :"); passwork = sc.nextLine();
        }while(!patternPasswork.matcher(getPasswork()).find());
    }

    public Account() {
    }

    public Account( long idAccount,String username, String passwork) {
        this.idAccount = idAccount;
        this.username = username;
        this.passwork = passwork;
    }

    public Account(String username, String passwork) {
        this.username = username;
        this.passwork = passwork;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "" +
                "idAccount=" + idAccount +
                ", username='" + username + '\'' +
                ", passwork='" + passwork + '\'' +
                "";
    }
}
