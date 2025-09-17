package test;

import vo.Friend;

import java.time.LocalDate;
import java.util.Scanner;

public class FriendTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name, sns;
        int age;
        LocalDate birthday;
        boolean type;

        int year, month, day;

        System.out.print("이름 : ");        name = sc.next();
        System.out.print("sns : ");    sns = sc.next();
        System.out.print("생년 : ");   year = sc.nextInt();
        System.out.print("생월 : ");   month = sc.nextInt();
        System.out.print("생일 : ");  day = sc.nextInt();
        birthday = LocalDate.of(year, month, day); // year, month, day
        System.out.print("나이 : ");        age = sc.nextInt();
        System.out.print("성격(외향:true, 내향:false) : ");        type = sc.nextBoolean();

        Friend f = new Friend(name, sns, birthday, age, type);
        f.output();


    }
}
