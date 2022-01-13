package PersonManagerApp;

import java.util.Scanner;

public class Person{
    Scanner sc = new Scanner(System.in);
    private String fullName;
    private DatePublic birthday;
    private String gender;
    private String nationality;
    private String placeOfOrigin;

    public void input(){
        System.out.print("Enter your FullName :"); fullName = sc.nextLine();
        System.out.println("Enter your birthday ");
        birthday = new DatePublic();
        birthday.input();
        System.out.print("Enter your gender :"); gender = sc.nextLine();
        System.out.print("Enter your nationality :"); nationality = sc.nextLine();
        System.out.print("Enter place Of Origin :"); placeOfOrigin = sc.nextLine();
    }

    public Person() {
    }

    public Person(String fullName, DatePublic birthday, String gender, String nationality, String placeOfOrigin) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.nationality = nationality;
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public DatePublic getBirthday() {
        return birthday;
    }

    public void setBirthday(DatePublic birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    @Override
    public String toString() {
        return "" +
                "fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", placeOfOrigin='" + placeOfOrigin + '\'' +
                "";
    }
}
