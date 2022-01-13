package PersonManagerApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class DatePublic {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
    Scanner sc = new Scanner(System.in);
    Pattern patternYear = Pattern.compile("^[0-9]{4}$");
    private int d;
    private int m;
    private int y;

    public void input(){
        do{
            System.out.print("Enter ngay :"); d = sc.nextInt();
        }while((getD()<1 || getD()>31));
        do{
        System.out.print("Enter thang :"); m = sc.nextInt();
        }while(m<1 || m>12 );
        do{
        System.out.print("Enter nam :"); y = sc.nextInt();
        }while(!patternYear.matcher(Integer.toString(getY())).find());
//        sc.nextLine();
    }

    public void output(){
        System.out.println(d+"/"+m+"/"+y);
    }

    public void CurrenDate(){
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        setD(day);
        setM(month);
        setY(year);
    }

    public long countSpaceYear(String after ,String before){
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(after);
            d2 = format.parse(before);
            long diff = d1.getTime() - d2.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffYears =diffDays/365;
            return diffYears;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public DatePublic() {
    }

    public DatePublic(int d, int m, int y) {
        this.d = d;
        this.m = m;
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ""+ d +
                "/" + m +
                "/" + y +"";
    }
}
