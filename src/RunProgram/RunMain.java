package RunProgram;

import Admin.AccountAdmin;
import Admin.PageManagerOfProvince;
import Client.AccountClient;
import Client.PageManagerOfClient;
import FileControll.FileController;
import PersonManagerApp.CMND;
import PersonManagerApp.Person;
import UserAccount.Account;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RunMain {
    static Scanner sc = new Scanner(System.in);
    static AccountClient accountClient = new AccountClient();
    static AccountAdmin accountAdmin = new AccountAdmin();
    static PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
    static PageManagerOfClient pageManagerOfClient = new PageManagerOfClient();
//    static Account account= new Account();

    public static void main(String[] args) {
        System.out.println("----------------------------CONG HOA XA HOI CHU NGHIA VIET NAM ------------------------------");
        int selectToLogin = 0;
        do{
            System.out.println("Select :" +
                    "1.Login" +
                    "\n\t\t2.Create Account with Client" +
                    "\n\t\t0.exit");
            System.out.print("Enter Select To Login : "); selectToLogin = sc.nextInt();
            switch(selectToLogin){
                case 1 :{
                    selectLogin();
                    break;
                }
                case 2 :{
                    pageManagerOfProvince.createAccountClientToCMND();
                    break;
                }
                case 0 : {
                    System.out.println("thank you gor using the service");
                    break;
                }
                default: {
                    System.out.println("error input !");
                }
            }
        }while(selectToLogin!=0);
    }

    // selectLogin
    static public void selectLogin(){
        int SelectLogin = 0;
        do{
            System.out.println("Choose Login:" +
                    "1.Admin" +
                    "\n\t\t\t 2.Client" +
                    "\n\t\t\t 3.forgot passwork of client" +
                    "\n\t\t\t 0.exit");
            System.out.print("Enter choose type Login : "); SelectLogin = sc.nextInt();
            switch (SelectLogin){
                case 1 : {
                    System.out.println("------Enter user Admin-------");
                    accountAdmin.checkLoginOfAdmin();
                    functionAdmin();
                    break;
                }
                case 2 : {
                    System.out.println("-------Enter user Client------ ");
                    Account accountLoginClient = accountClient.checkLoginOfClient();
                    functionClient(accountLoginClient);
                    break;
                }
                case 3 : {
                    System.out.println("-----------Forgot passwork--------------");
                    System.out.print("Enter your Number CMND :"); long numberCMND = sc.nextLong();
                    pageManagerOfClient.forgotPassworkOfClient(numberCMND);
                    break;
                }
                case 0: {
                    break;
                }
            }
        }while(SelectLogin!=0);
    }

    static void functionAdmin(){
        System.out.println("----------FUNCTIONS ADMIN ---------------");
        int selectFunctionAdmin = 0;
        do{
            System.out.println("Select function :" +
                    "1.insert CMND" +
                    "\n\t\t\t\t 2.update By Number CMND " +
                    "\n\t\t\t\t 3.remove By Number CMND " +
                    "\n\t\t\t\t 4.find By Id" +
                    "\n\t\t\t\t 5.filter By Name" +
                    "\n\t\t\t\t 6.filter By Age" +
                    "\n\t\t\t\t 7.filter By Home Town" +
                    "\n\t\t\t\t 8.filter Expires CMND" +
                    "\n\t\t\t\t 9.filter By Gender" +
                    "\n\t\t\t\t 10.sort By Name" +
                    "\n\t\t\t\t 11.sort By Age" +
                    "\n\t\t\t\t 12.sort By ExpiredYear" +
                    "\n\t\t\t\t 13.display All CMND" +
                    "\n\t\t\t\t 14.show All Account Client" +
                    "\n\t\t\t\t 15.show All Account Admin" +
                    "\n\t\t\t\t 16.create Account Client To CMND" +
                    "\n\t\t\t\t 17.create Account Admin" +
                    "\n\t\t\t\t 18.Edit Passwork Of Client " +
                    "\n\t\t\t\t 19.Edit Passwork Of Admin " +
                    "\n\t\t\t\t0.exit"
            );
            System.out.print("Enter select function : "); selectFunctionAdmin = sc.nextInt();
            switch(selectFunctionAdmin){
                case 1 :{
                    pageManagerOfProvince.insertCMND();
                    break;
                }
                case 2 :{
                    String works ;
                    long numberCMND;
                    do{
                        System.out.print("Enter number CMND alrealy exists :"); numberCMND = sc.nextLong();
                    }while(!pageManagerOfProvince.checkNumberCMNDExist("ListCMND.txt",numberCMND));
                    sc.nextLine();
                    do{
                        System.out.print("Enter property need update (fullname,birthday,gender,nationality,place of origin) : ");
                        works = sc.nextLine();
                    }while(works.compareTo("fullname")!=0 && works.compareTo("birthday")!=0 && works.compareTo("gender")!=0 && works.compareTo("nationality")!=0 &&works.compareTo("place of origin")!=0 );
                    pageManagerOfProvince.updateDataCMND(numberCMND,works);
                    break;
                }
                case 3 :{
                    pageManagerOfProvince.removeById();
                    break;
                }
                case 4 :{
                    System.out.print("Enter Number CMND :"); long SCMND = sc.nextLong();
                    pageManagerOfProvince.findById(SCMND);
                    break;
                }
                case 5 :{
                    sc.nextLine();
                    System.out.print("Enter search words :");String words = sc.nextLine();
                    pageManagerOfProvince.filterByName(words);
                    break;
                }
                case 6 :{
                    System.out.print("Enter number age :"); long age = sc.nextLong();
                    String invariant;
                    sc.nextLine();
                    do{
                        System.out.print("Enter increase or decrease or not  :"); invariant = sc.nextLine();
                    }while(invariant.compareTo("decrease")!=0 && invariant.compareTo("increase")!=0 && invariant.compareTo("not")!=0);
                    pageManagerOfProvince.filterByAge(age,invariant);
                    break;
                }
                case 7 :{
                    sc.nextLine();
                    System.out.print("Enter words Home Town :"); String words = sc.nextLine();
                    pageManagerOfProvince.filterByHomeTown(words);
                    break;
                }
                case 8 :{
                    pageManagerOfProvince.filterExpiresCMND();
                    break;
                }
                case 9 :{
                    String gender;
                    sc.nextLine();
                    do{
                        System.out.print("Enter Gender male or female :"); gender = sc.nextLine();
                    }while(gender.compareTo("nam")!=0 && gender.compareTo("nu")!=0 && gender.compareTo("male")!=0 && gender.compareTo("female")!=0);
                    pageManagerOfProvince.filterByGender(gender);
                    break;
                }
                case 10 :{
                    String invariant;
                    sc.nextLine();
                    do{
                        System.out.print("Enter fullName increase or decrease :"); invariant = sc.nextLine();
                    }while(invariant.compareTo("decrease")!=0 && invariant.compareTo("increase")!=0);
                    pageManagerOfProvince.sortByName("invariant");
                    break;
                }
                case 11 :{
                    String invariant;
                    sc.nextLine();
                    do{
                        System.out.print("Enter Age increase or decrease :"); invariant = sc.nextLine();
                    }while(invariant.compareTo("decrease")!=0 && invariant.compareTo("increase")!=0);
                    pageManagerOfProvince.sortByAge("invariant");
                    break;
                }
                case 12 :{
                    String invariant;
                    sc.nextLine();
                    do{
                        System.out.print("Enter Expires years increase or decrease :"); invariant = sc.nextLine();
                    }while(invariant.compareTo("decrease")!=0 && invariant.compareTo("increase")!=0);
                    pageManagerOfProvince.sortByExpiredYear("invariant");
                    break;
                }
                case 13 :{
                    pageManagerOfProvince.displayAllCMND();
                    break;
                }
                case 14 :{
                    pageManagerOfProvince.showAllAccountClient();
                    break;
                }
                case 15 :{
                    pageManagerOfProvince.showAllAccountAdmin();
                    break;
                }
                case 16 :{
                    pageManagerOfProvince.createAccountClientToCMND();
                    break;
                }
                case 17 :{
                    pageManagerOfProvince.createAccountAdmin();
                    break;
                }
                case 18 :{
                    Pattern patternPasswork = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$");
                    String wordUser ;
                    String wordPasswork;
                    sc.nextLine();
                    do{
                        System.out.print("Enter Username of you : "); wordUser = sc.nextLine();
                    }while(!pageManagerOfProvince.checkUsernameAccountxist("AccountClient.txt",wordUser));
                    do{
                        System.out.print("Enter new Passwork :"); wordPasswork = sc.nextLine();
                    }while(!patternPasswork.matcher(wordPasswork).find());
                    pageManagerOfProvince.editPassworkAccount("AccountClient.txt",wordUser,wordPasswork);
                    break;
                }
                case 19 :{
                    Pattern patternPasswork = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$");
                    String wordUser ;
                    String wordPasswork;
                    do{
                        System.out.print("Enter Username of you : ");
                        wordUser = sc.nextLine();
                    }while(!pageManagerOfProvince.checkUsernameAccountxist("AccountAdmin.txt",wordUser));
                    do{
                        System.out.print("Enter new Passwork :"); wordPasswork = sc.nextLine();
                    }while(!patternPasswork.matcher(wordPasswork).find());
                    pageManagerOfProvince.editPassworkAccount("AccountAdmin.txt",wordUser,wordPasswork);
                    break;
                }
                case 0 : {
                    System.out.println("thank you gor using the service");
                    break;
                }
                default: {
                    System.out.println("error input !");
                }
            }
        }while(selectFunctionAdmin!=0);
    }

    static void functionClient(Account accountLoginClient){
        System.out.println("----------FUNCTIONS ADMIN ---------------");
        int selectFunctionClient = 0;
        do{
            System.out.println("Select function :" +
                    "1.show your Information CMND" +
                    "\n\t\t\t\t 2.show Account " +
                    "\n\t\t\t\t 3.Edit your infomation CMND " +
                    "\n\t\t\t\t 4.Edit your Passwork of Account " +
                    "\n\t\t\t\t 0.exits"
            );
            System.out.print("Enter select function : "); selectFunctionClient = sc.nextInt();
            switch(selectFunctionClient){
                case 1 : {
                    pageManagerOfClient.showYourInformationCMND(accountLoginClient);
                    break;
                }
                case 2 : {
                    System.out.println(accountLoginClient);
                    break;
                }
                case 3 : {
                    pageManagerOfClient.updateDataCMNDWithClient(accountLoginClient);
                    break;
                }
                case 4: {
                    pageManagerOfClient.editPassworkOfAccount(accountLoginClient);
                    break;
                }
                case 0 : {
                    System.out.println("Thanh you alrealy service !");
                    break;
                }
                default:{
                    System.out.println("Error input !");
                    break;
                }
            }
        }while(selectFunctionClient!=0);

    }
}
