package Client;

import FileControll.FileController;
import UserAccount.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountClient {
    FileController fileController = new FileController();
    public void CreateAccountOfClient(){
        Account account = new Account();
        account.input("AccountClient.txt");
        fileController.WriteAccountToFile("AccountClient.txt" ,account);
    }
    public void ReadAccountOfClient(){
        fileController.ReadAccountToFile("AccountClient.txt").forEach(account -> {
            System.out.println(account);
        });
    }

    public Account checkLoginOfClient(){
        Account accountCheck = new Account();
        do{
            accountCheck.input();
        }while(fileController.CheckLogin("AccountClient.txt",accountCheck)!=true);
        List<Account> listAccount = fileController.ReadAccountToFile("AccountClient.txt");
        for(Account acc : listAccount)
            if(accountCheck.getUsername().compareTo(acc.getUsername())==0 && accountCheck.getPasswork().compareTo(acc.getPasswork())==0)
                return acc;
        return null;
    }
}
