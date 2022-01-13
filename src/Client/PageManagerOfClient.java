package Client;

import Admin.PageManagerOfProvince;
import FileControll.FileController;
import PersonManagerApp.CMND;
import UserAccount.Account;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PageManagerOfClient implements IFunctionOfClient{
    Scanner sc = new Scanner(System.in);
    FileController fileController = new FileController();

    PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
    @Override
    public void showYourInformationCMND(Account account) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        for(CMND cmnd : listCMND)
            if(cmnd.getIdAccount() == account.getIdAccount())
                System.out.println(cmnd);
    }

    @Override
    public void updateDataCMNDWithClient(Account account) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        CMND newCMND = new CMND();
        for(CMND cmnd : listCMND)
            if(cmnd.getIdAccount() == account.getIdAccount()){
                newCMND = cmnd; break;
            }
        String works ;
        do{
            System.out.print("Enter property need update (fullname,birthday,gender,nationality,place of origin) : ");
            works = sc.nextLine();
        }while(works.compareTo("fullname")!=0 && works.compareTo("birthday")!=0 && works.compareTo("gender")!=0 && works.compareTo("nationality")!=0 &&works.compareTo("place of origin")!=0 );
        pageManagerOfProvince.updateDataCMND(newCMND.getNumberCMND(),works);
    }

    @Override
    public void editPassworkOfAccount(Account account) {
        Pattern patternPasswork = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$");
        String wordPasswork;
        do{
            System.out.print("Enter new Passwork :"); wordPasswork = sc.nextLine();
        }while(!patternPasswork.matcher(wordPasswork).find());
        pageManagerOfProvince.editPassworkAccount("AccountClient.txt",account.getUsername(),wordPasswork);
    }

    @Override
    public void forgotPassworkOfClient(long numberCMND) {
        List<Account> listAccount = fileController.ReadAccountToFile("AccountClient.txt");
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        CMND newCMND = null;
        Account newAccount = null;
        for(CMND cmnd :listCMND)
            if(numberCMND ==cmnd.getNumberCMND())
                newCMND = cmnd;
        for(Account account : listAccount)
            if(account.getIdAccount() == newCMND.getIdAccount())
                newAccount = account;
        if(newAccount.getIdAccount()==0){
            System.out.println("not exists!"); return;
        }
        Pattern patternPasswork = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$");
        String wordPasswork;
        do{
            System.out.print("Enter new Passwork :"); wordPasswork = sc.nextLine();
        }while(!patternPasswork.matcher(wordPasswork).find());
        pageManagerOfProvince.editPassworkAccount("AccountClient.txt",newAccount.getUsername(),wordPasswork);
    }
}
