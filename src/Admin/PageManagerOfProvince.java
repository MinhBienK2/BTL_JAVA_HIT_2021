package Admin;

import Client.AccountClient;
import FileControll.FileController;
import PersonManagerApp.CMND;
import PersonManagerApp.DatePublic;
import UserAccount.Account;
import org.w3c.dom.ls.LSOutput;

import java.io.PrintWriter;
import java.util.*;

public class PageManagerOfProvince implements IFunctionOfAdmin{
    Scanner sc = new Scanner(System.in);
    FileController fileController = new FileController();
    AccountClient accountClient = new AccountClient();
    AccountAdmin accountAdmin = new AccountAdmin();
    DatePublic datePublic = new DatePublic();

    public void findKeyWithCMND(String findName,String address){
        for(CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt")){
            if(address.compareTo("fullName")==0)
                if(cmnd.getPerson().getFullName().indexOf(findName)!=-1 || cmnd.getPerson().getFullName().toLowerCase().indexOf(findName)!=-1)
                    System.out.println(cmnd);
            if(address.compareTo("nationality")==0)
                if(cmnd.getPerson().getNationality().indexOf(findName)!=-1 || cmnd.getPerson().getNationality().toLowerCase().indexOf(findName)!=-1)
                    System.out.println(cmnd);
            if(address.compareTo("placeOfOrigin")==0)
                if(cmnd.getPerson().getPlaceOfOrigin().indexOf(findName)!=-1 || cmnd.getPerson().getPlaceOfOrigin().toLowerCase().indexOf(findName)!=-1)
                    System.out.println(cmnd);
        }

    }

    public boolean checkIdAccountExist(String filename,long number){
        List<Account> listAccount = fileController.ReadAccountToFile(filename);
        for(Account account : listAccount)
            if(account.getIdAccount() == number)
                return true;
        return false;
    }

    public boolean checkNumberCMNDExist(String filename,long number){
        List<CMND> listCMND = fileController.ReadCMNDToFIle(filename);
        for(CMND cmnd : listCMND)
            if(cmnd.getNumberCMND() == number)
                return true;
        return false;
    }
    public boolean checkUsernameAccountxist(String filename,String username){
        List<Account> listAccount = fileController.ReadAccountToFile(filename);
        for(Account account : listAccount)
            if(account.getUsername().compareTo(username)==0)
                return true;
        return false;
    }

    public void sortByIdAccountOfCMND(List<CMND> listCMND){
//         listCMND = fileController.ReadCMNDToFIle(filename);
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    if(o1.getIdAccount() > o2.getIdAccount())
                        return 1;
                    else if(o1.getIdAccount() < o2.getIdAccount())
                        return -1;
                    else return 0;
                }
            });
    }

    @Override
    public void insertCMND() {
        CMND cmnd = new CMND();
        cmnd.setNumberCMND(cmnd.createCMNDByRandomId());
        cmnd.input();
        String checkCreateAccount;
        do{
            System.out.println("------Do you want to create an account now?-----");
            System.out.println("Enter true/false :");checkCreateAccount = sc.nextLine();
        }while(checkCreateAccount.compareTo("true")!=0 && checkCreateAccount.compareTo("false")!=0);
        boolean isCheckCreateAccount = Boolean.parseBoolean(checkCreateAccount);
        if(isCheckCreateAccount){
            long countNumber=0;
            accountClient.CreateAccountOfClient();
            do{
                countNumber++;
            }while(checkIdAccountExist("ListCMND.txt",countNumber) || checkIdAccountExist("AccountClient.txt",countNumber));
            cmnd.setIdAccount(countNumber);
            List<Account> listAccount = fileController.ReadAccountToFile("AccountClient.txt");
            listAccount.get(listAccount.size()-1).setIdAccount(countNumber);
            fileController.UpdateAccountToFile(listAccount,"AccountClient.txt");
            System.out.println("Account successfully created !");
        }
        else
            System.out.println("CMND successfully created !");
        fileController.WriteCMNDToFile("ListCMND.txt",cmnd);
        //    Sapxep
//        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
//        sortByIdAccountOfCMND(listCMND);
//        fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
    }

    @Override
    public void removeById() {
        long number;
        System.out.print("Enter Number id or Number CMND : "); number = sc.nextLong();
        fileController.DeleteListCMNDByNumberCMND("ListCMND.txt",number);
    }

    @Override
    public void findById(long numberId) {
//        System.out.println(fileController.ReadCMNDToFIle("ListCMND.txt"));
        for(CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt"))
            if(cmnd.getNumberCMND()==numberId)
                System.out.println(cmnd);
    }

    @Override
    public void filterByName(String filterName) {
        findKeyWithCMND(filterName,"fullName");
    }

    @Override
    public void filterByAge(long filterAge, String isCheck) {
        DatePublic dateCurren = new DatePublic();
        dateCurren.CurrenDate();
        for(CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt")){
            if(isCheck.compareTo("increase")==0){
                if(datePublic.countSpaceYear(dateCurren.toString(),cmnd.getPerson().getBirthday().toString())>=filterAge)
                    System.out.println(cmnd);
            }
            else if(isCheck.compareTo("decrease")==0){
                if(datePublic.countSpaceYear(dateCurren.toString(),cmnd.getPerson().getBirthday().toString()) <= filterAge)
//                    System.out.println(cmnd +"\n"+datePublic.countSpaceYear(dateCurren.toString(),cmnd.getPerson().getBirthday().toString()));
                    System.out.println(cmnd);
            }
            else{
                if(datePublic.countSpaceYear(dateCurren.toString(),cmnd.getPerson().getBirthday().toString())==filterAge)
                    System.out.println(cmnd);
            }
        }
    }

    @Override
    public void filterByHomeTown(String filterHomeTown) {
        findKeyWithCMND(filterHomeTown,"placeOfOrigin");
    }

    @Override
    public void filterExpiresCMND() {
        DatePublic dateCurren = new DatePublic();
        dateCurren.CurrenDate();
        for (CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt"))
            if (datePublic.countSpaceYear(dateCurren.toString(), cmnd.getDateOfIssue().toString()) >= 7)
                System.out.println(cmnd);
    }

    @Override
    public void filterByGender(String filterGender) {
        for (CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt")){
            if(filterGender.compareTo("nam")==0 || filterGender.compareTo("male")==0)
                if(cmnd.getPerson().getGender().compareTo("nam")==0 || cmnd.getPerson().getGender().compareTo("male")==0)
                    System.out.println(cmnd);
            if(filterGender.compareTo("nu")==0 || filterGender.compareTo("female")==0)
                if(cmnd.getPerson().getGender().compareTo("nu")==0 || cmnd.getPerson().getGender().compareTo("female")==0)
                    System.out.println(cmnd);
        }
    }

    @Override
    public void sortByName(String invariant) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        if(invariant.compareTo("decrease")==0){
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    return o1.getPerson().getFullName().compareTo(o2.getPerson().getFullName());
                }
            });
        }
//        increase
        else{
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    return o2.getPerson().getFullName().compareTo(o1.getPerson().getFullName());
                }
            });
        }
        for (CMND cmnd : listCMND)
            System.out.println(cmnd);
    }

    @Override
    public void sortByAge(String invariant) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        if(invariant.compareTo("decrease")==0){
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    if(o1.ageOfYou(o1.getNumberCMND()) < o2.ageOfYou(o2.getNumberCMND()))
                        return 1;
                    else if(o1.ageOfYou(o1.getNumberCMND()) > o2.ageOfYou(o2.getNumberCMND()))
                        return -1;
                    else return 0;
                }
            });
        }
//        increase
        else{
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    if(o1.ageOfYou(o1.getNumberCMND()) > o2.ageOfYou(o2.getNumberCMND()))
                        return 1;
                    else if(o1.ageOfYou(o1.getNumberCMND()) < o2.ageOfYou(o2.getNumberCMND()))
                        return -1;
                    else return 0;
                }
            });
        }
        for (CMND cmnd : listCMND)
            System.out.println(cmnd);
    }

    @Override
    public void sortByExpiredYear(String invariant) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        if(invariant.compareTo("decrease")==0){
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    if(o1.expiresYearsOfYou(o1.getNumberCMND()) < o2.expiresYearsOfYou(o2.getNumberCMND()))
                        return 1;
                    else if(o1.expiresYearsOfYou(o1.getNumberCMND()) > o2.expiresYearsOfYou(o2.getNumberCMND()))
                        return -1;
                    else return 0;
                }
            });
        }
//        increase
        else{
            Collections.sort(listCMND, new Comparator<CMND>() {
                @Override
                public int compare(CMND o1, CMND o2) {
                    if(o1.expiresYearsOfYou(o1.getNumberCMND()) > o2.expiresYearsOfYou(o2.getNumberCMND()))
                        return 1;
                    else if(o1.expiresYearsOfYou(o1.getNumberCMND()) < o2.expiresYearsOfYou(o2.getNumberCMND()))
                        return -1;
                    else return 0;
                }
            });
        }
        for (CMND cmnd : listCMND)
            System.out.println(cmnd);
    }

    @Override
    public void displayAllCMND() {
        for(CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt"))
            System.out.println(cmnd);
    }

    @Override
    public void showAllAccountClient() {
        for(Account account : fileController.ReadAccountToFile("AccountClient.txt"))
            System.out.println(account);
    }

    @Override
    public void showAllAccountAdmin() {
        for(Account account : fileController.ReadAccountToFile("AccountAdmin.txt"))
            System.out.println(account);
    }

    @Override
    public void createAccountClientToCMND() {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        List<Account> listAccount = fileController.ReadAccountToFile("AccountClient.txt");
        long numberCMND;
        long idAccountOfCMND = 0;
        boolean ischeck = false;
        do{
            System.out.print("Enter Number CMND : "); numberCMND = sc.nextLong();
            for(CMND cmnd : listCMND)
                if(numberCMND == cmnd.getNumberCMND()){
                    ischeck = true;
                    idAccountOfCMND = cmnd.getIdAccount();
                }
        }while(!ischeck);
        if(idAccountOfCMND!=0l){
            System.out.println("Accout already exists !"); return;
        }
        do{
            idAccountOfCMND+=1;
        }while(checkIdAccountExist("ListCMND.txt",idAccountOfCMND) || checkIdAccountExist("AccountClient.txt",idAccountOfCMND));
        for(CMND cmnd : listCMND)
            if(numberCMND == cmnd.getNumberCMND())
                cmnd.setIdAccount(idAccountOfCMND);
        fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        Account account = new Account();
        account.input("AccountClient.txt");
        account.setIdAccount(idAccountOfCMND);
        fileController.WriteAccountToFile("AccountClient.txt" ,account);
    }

    @Override
    public void createAccountAdmin() {
        accountAdmin.CreateAccountOfAdmin();
    }

    @Override
    public void updateDataCMND(long numberCMND , String works) {
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        if(works.compareTo("fullname")==0){
            System.out.print("Enter new your fullname : ");String name = sc.nextLine();
            for(CMND cmnd : listCMND)
                if(cmnd.getNumberCMND() == numberCMND)
                    cmnd.getPerson().setFullName(name);
            fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        }
        if(works.compareTo("birthday")==0){
            System.out.println("Enter new your birthday : ");
            DatePublic dateCMND = new DatePublic();
            dateCMND.input();
            for(CMND cmnd : listCMND)
                if(cmnd.getNumberCMND() == numberCMND)
                    cmnd.getPerson().setBirthday(dateCMND);
            fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        }
        if(works.compareTo("gender")==0){
            System.out.print("Enter new your gender : ");String gender = sc.nextLine();
            for(CMND cmnd : listCMND)
                if(cmnd.getNumberCMND() == numberCMND)
                    cmnd.getPerson().setGender(gender);
            fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        }
        if(works.compareTo("nationality")==0){
            System.out.print("Enter new your nationality : ");String nationality = sc.nextLine();
            for(CMND cmnd : listCMND)
                if(cmnd.getNumberCMND() == numberCMND)
                    cmnd.getPerson().setNationality(nationality);
            fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        }
        if(works.compareTo("place of origin")==0){
            System.out.print("Enter new your place of origin : ");String origin = sc.nextLine();
            for(CMND cmnd : listCMND)
                if(cmnd.getNumberCMND() == numberCMND)
                    cmnd.getPerson().setPlaceOfOrigin(origin);
            fileController.UpdateCMNDToFile(listCMND,"ListCMND.txt");
        }
    }

    @Override
    public void editPassworkAccount(String filename, String username ,String passwork) {
        List<Account> listAccount = fileController.ReadAccountToFile(filename);
        String comfirmPass = null;
        do{
            System.out.print("Enter comfirm Passwork :"); comfirmPass = sc.nextLine();
        }while(passwork.compareTo(comfirmPass)!=0);
        for(Account account : listAccount)
            if(account.getUsername().compareTo(username)==0)
                account.setPasswork(passwork);
        fileController.UpdateAccountToFile(listAccount,filename);
    }
}
