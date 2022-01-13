package PersonManagerApp;

import FileControll.FileController;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CMND {
    Scanner sc = new Scanner(System.in);
    FileController fileController = new FileController();
    DatePublic datePublic = new DatePublic();
    private long idAccount;
    private long NumberCMND;
    private Person person;
    private DatePublic dateOfIssue;

    public void input(){
        person = new Person();
        person.input();
        dateOfIssue = new DatePublic();
        System.out.println("Enter Date of issue :");
        dateOfIssue.CurrenDate();
    }

    public long ageOfYou(long number){
        DatePublic dateCurren = new DatePublic();
        dateCurren.CurrenDate();
        for (CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt")){
            if(cmnd.getNumberCMND() == number)
                return datePublic.countSpaceYear(dateCurren.toString(), cmnd.getPerson().getBirthday().toString());
        }
        return -1;
    }
    public long expiresYearsOfYou(long number){
        DatePublic dateCurren = new DatePublic();
        dateCurren.CurrenDate();
        for (CMND cmnd : fileController.ReadCMNDToFIle("ListCMND.txt")){
            if(cmnd.getNumberCMND() == number)
                return datePublic.countSpaceYear(dateCurren.toString(), cmnd.getDateOfIssue().toString());
        }
        return -1;
    }

    public long createCMNDByRandomId(){
        List<CMND> listCMND = fileController.ReadCMNDToFIle("ListCMND.txt");
        long diffNumber;
        boolean ischeck = false;
        do{
            String ranNum = Integer.toString(ThreadLocalRandom.current().nextInt(100000000,999999999));
            String ranNum1 = Integer.toString(ThreadLocalRandom.current().nextInt(100,999));
            diffNumber =  Long.parseLong(ranNum1+ranNum);
            for(CMND cmnd :listCMND)
                if(cmnd.getNumberCMND() == diffNumber)
                    ischeck = true;
        }while(ischeck);
        return diffNumber;
    }

    public CMND() {
    }

    public CMND(long idAccount, long numberCMND, Person person, DatePublic dateOfIssue) {
        this.idAccount = idAccount;
        NumberCMND = numberCMND;
        this.person = person;
        this.dateOfIssue = dateOfIssue;
    }

    public long getNumberCMND() {
        return NumberCMND;
    }

    public void setNumberCMND(long numberCMND) {
        NumberCMND = numberCMND;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DatePublic getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(DatePublic dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "CMND{" +
                "idAccount=" + idAccount +
                ", NumberCMND=" + NumberCMND +
                ", person=" + person +
                ", dateOfIssue=" + dateOfIssue +
                '}';
    }
}
