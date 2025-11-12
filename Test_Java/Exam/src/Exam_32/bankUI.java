package Exam_32;

import java.util.ArrayList;
import java.util.Scanner;

public class bankUI {
    User user = new User();
    Scanner sc = new Scanner(System.in);
    bankService bs = new bankService();

    public bankUI(){
        menu();
    }

    public void menu(){
        String choice;
        while(true){
            System.out.print("=======은행계좌관리=======\n" +
                    "1.계좌등록\n" +
                    "2.전체출력\n" +
                    "3.입금\n" +
                    "4.출금\n" +
                    "5.송금\n" +
                    "6.프로그램종료\n" +
                    "=======================\n" +
                    "선택 : ");
            choice = sc.next();

            switch(choice){
                case "1": resist(); break;
                case "2": output(); break;
                case "3": deposit(); break;
                case "4": withdraw(); break;
                case "5": remit(); break;
                case "6":
                    System.out.println("프로그램을 종료합니다."); return;
                default:
                    System.out.println("메뉴를 잘못 선택하였습니다."); break;
            }
        }
    }

    public void resist(){
        String name, accNo;
        int balance;

        System.out.print("계좌주 이름 입력 : ");
        name = sc.next();
        System.out.print("계좌 번호 입력 : ");
        accNo = sc.next();
        if(bs.check(accNo)){
            System.out.println("이미 존재하는 계좌번호입니다.");
            return;
        }
        System.out.print("잔고입력 : ");
        balance = sc.nextInt();

        User user = new User(name, accNo, balance);
        bs.userAdd(user);
    }

    public void output(){
        ArrayList<User> as = bs.print();
        for(User user : as){
            System.out.println(user);
        }
    }

    public void deposit(){
        System.out.print("입금 할 계좌번호 입력 : ");
        String accNo = sc.next();
        if(!bs.check(accNo)){
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        System.out.print("입금금액 입력 : ");
        int depositPrice = sc.nextInt();
        bs.inMoney(accNo, depositPrice);
    }
    public void withdraw(){
        System.out.print("출금 할 계좌번호 입력 : ");
        String accNo = sc.next();
        if(!bs.check(accNo)){
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        System.out.print("출금금액 입력 : ");
        int depositPrice = sc.nextInt();
        if(!bs.outMoney(accNo, depositPrice)){
            System.out.println("잔액이 부족합니다.");
            return;
        }
    }
    public void remit(){
        System.out.print("송금 할 계좌번호 입력 : ");
        String sendAccNo = sc.next();
        if(!bs.check(sendAccNo)){
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }

        System.out.print("송금 받을 계좌번호 입력 : ");
        String receiveAccNo = sc.next();
        if(!bs.check(receiveAccNo)){
            System.out.println("존재하지 않는 계좌번호입니다.");
            return;
        }
        System.out.print("송금금액 입력 : ");
        int depositPrice = sc.nextInt();

        if(!bs.outMoney(sendAccNo, depositPrice)){
            System.out.println("잔액이 부족합니다.");
            return;
        }
        bs.inMoney(receiveAccNo, depositPrice);
    }
}
