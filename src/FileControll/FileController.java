package FileControll;

import Admin.PageManagerOfProvince;
import PersonManagerApp.CMND;
import PersonManagerApp.DatePublic;
import PersonManagerApp.Person;
import UserAccount.Account;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {
        private FileWriter fileWriter;
        private BufferedWriter bufferedWriter;
        private PrintWriter printWriter;
        private Scanner scanner;

    public Scanner getScanner(){
        return this.scanner;
    }

    public void UpdateAccountToFile(List<Account> accounts , String filename){
        File file = new File(filename);
        if(file.exists()){
            file.delete();
        }
        for(Account account : accounts){
            WriteAccountToFile(filename,account);
        }
    }

    public void UpdateCMNDToFile(List<CMND> cmnds , String filename){
        File file = new File(filename);
        if(file.exists()){
            file.delete();
        }
        for(CMND cmnd : cmnds){
            WriteCMNDToFile(filename,cmnd);
        }
    }

    public void OpenFileToWrite(String fileName){
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void OpenFileToDelete(String fileName){
        try {
            fileWriter = new FileWriter(fileName, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void CloseFileAfterWrite(){
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void OpenFileToRead(String fileName){
        try {
            scanner = new Scanner(Paths.get(fileName));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void CloseFileAfterRead(){
        scanner.close();
    }

//    Account

    public void WriteAccountToFile(String filename, Account acc){
//        PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
//        long count = 0;
//        List<Account> listAccount = ReadAccountToFile(filename);
//        do{
//            count+=1;
//        }while(pageManagerOfProvince.checkIdAccountExist(filename,count));
//        acc.setIdAccount(count);
//        OpenFileToWrite(filename);
//        printWriter.println(acc.getIdAccount() + "|" + acc.getUsername() + "|" + acc.getPasswork());
//        CloseFileAfterWrite();

        PageManagerOfProvince pageManagerOfProvince = new PageManagerOfProvince();
        long count = 0;
        if(acc.getIdAccount()!=0)
            count = acc.getIdAccount();
        else {
            List<Account> listAccount = ReadAccountToFile(filename);
            do{
                count+=1;
            }while(pageManagerOfProvince.checkIdAccountExist(filename,count));
        }
        acc.setIdAccount(count);
        OpenFileToWrite(filename);
        printWriter.println(acc.getIdAccount() + "|" + acc.getUsername() + "|" + acc.getPasswork());
        CloseFileAfterWrite();
    }

    public List<Account> ReadAccountToFile(String filename){
        OpenFileToRead(filename);
        List<Account> listAccount = new ArrayList<>();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String []a = s.split("\\|");
            listAccount.add(new Account(Long.parseLong(a[0]),a[1],a[2]));
        }
        CloseFileAfterRead();
        return listAccount;
    }

    //checklogin
    public boolean CheckLogin(String filename,Account accountCheck){
        List<Account> fakeReadAccount = ReadAccountToFile(filename);
        for(Account account : fakeReadAccount){
            if(accountCheck.getUsername().compareTo(account.getUsername())==0 && accountCheck.getPasswork().compareTo(account.getPasswork())==0){
                return true;
            }
        }
        return false;
    }

    //cmnd

    public void WriteCMNDToFile(String filename,CMND cmnd){
        OpenFileToWrite(filename);
        printWriter.println(cmnd.getIdAccount()
                +"|"+ cmnd.getNumberCMND()
                +"|"+ cmnd.getPerson().getFullName()
                +"|"+ cmnd.getPerson().getBirthday()
                +"|"+ cmnd.getPerson().getGender()
                +"|"+ cmnd.getPerson().getNationality()
                +"|"+ cmnd.getPerson().getPlaceOfOrigin()
                +"|"+ cmnd.getDateOfIssue()
        );
        CloseFileAfterWrite();
    }

//    public List<Account> ReadCMNDToFIle(String filename){
    public List<CMND> ReadCMNDToFIle(String filename){
        OpenFileToRead(filename);
        List<CMND> listCMND = new ArrayList<>();
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String []a = s.split("\\|");
            String []b = a[3].split("/");
            String []c = a[7].split("/");
            listCMND.add(
                    new CMND(
                            Long.parseLong(a[0]),
                            Long.parseLong(a[1]),
                            new Person(
                                    a[2],
                                    (new DatePublic(Integer.parseInt(b[0]),Integer.parseInt(b[1]),Integer.parseInt(b[2]))),
                                    a[4],
                                    a[5],
                                    a[6]),
                            (new DatePublic(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2])))
                    )
            );
        }
        CloseFileAfterRead();
        return listCMND;
    }

    // delete
    public void DeleteAccountById(String filename,Long number){
        List<Account> listAccount = ReadAccountToFile(filename);
        for(int i=0;i<listAccount.size();i++)
            if(listAccount.get(i).getIdAccount()==number){
                listAccount.remove(i);
                break;
            }
        OpenFileToDelete(filename);
        for(Account account : listAccount)
            printWriter.println(account.getIdAccount() + "|" + account.getUsername() + "|" + account.getPasswork());
        CloseFileAfterWrite();
    }

    public void DeleteListCMNDByNumberCMND(String filename,Long number){
        List<CMND> listCMND = ReadCMNDToFIle(filename);
        Long gan = null;
        for(int i=0;i<listCMND.size();i++)
            if(listCMND.get(i).getNumberCMND()==number){
                gan = listCMND.get(i).getIdAccount();
                listCMND.remove(i);
                break;
            }
        DeleteAccountById("AccountClient.txt",gan);
        OpenFileToDelete(filename);
        for(CMND cmnd : listCMND)
            printWriter.println(cmnd.getIdAccount()
                    +"|"+ cmnd.getNumberCMND()
                    +"|"+ cmnd.getPerson().getFullName()
                    +"|"+ cmnd.getPerson().getBirthday()
                    +"|"+ cmnd.getPerson().getGender()
                    +"|"+ cmnd.getPerson().getNationality()
                    +"|"+ cmnd.getPerson().getPlaceOfOrigin()
                    +"|"+ cmnd.getDateOfIssue()
            );
        CloseFileAfterWrite();
    }

}
