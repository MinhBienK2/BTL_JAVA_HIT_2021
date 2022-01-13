package Admin;

import FileControll.FileController;
import UserAccount.Account;

import java.util.List;

public class AccountAdmin {
    FileController fileController = new FileController();

    public void CreateAccountOfAdmin(){
        Account account = new Account();
        account.input("AccountAdmin.txt");
        fileController.WriteAccountToFile("AccountAdmin.txt" ,account);
    }

    public void ReadAccountOfAdmin(){
        fileController.ReadAccountToFile("AccountAdmin.txt").forEach(account -> {
            System.out.println(account);
        });
    }

    public void checkLoginOfAdmin(){
        Account accountCheck = new Account();
        do{
            accountCheck.input();
        }while(fileController.CheckLogin("AccountAdmin.txt",accountCheck)!=true);
    }
}
