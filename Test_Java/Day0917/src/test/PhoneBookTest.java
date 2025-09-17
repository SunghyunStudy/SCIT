package test;

import vo.PhoneBook;

import java.util.Scanner;

public class PhoneBookTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name, phone, relation, address, memo;   // 임시 지역변수 (멤버변수 아님)

        // 1) 입력
        System.out.print("이름 : ");        name = sc.next();
        System.out.print("전화번호 : ");    phone = sc.next();
        System.out.print("관계 : ");        relation = sc.next();
        System.out.print("주소 : ");        address = sc.next();
        System.out.print("메모 : ");        memo = sc.next();


        // 2)  객체 생성 후 입력받은 데이터로 setting
        PhoneBook pb = new PhoneBook();     // 멤버변수의 초기값은?
        pb.setName(name);
        pb.setPhone(phone);
        pb.setRelation(relation);
        pb.setAddress(address);
        pb.setMemo(memo);


        // 3) 출력
//        System.out.printf("이름 : %s, 전화번호 : %s, 관계 : %s, 주소 : %s, 메모 : %s %n",
//                pb.getName(), pb.getPhone(), pb.getRelation(), pb.getAddress(), pb.getMemo());
        // output(pb);   // 출력 메소드 ver1
        pb.output();   // 출력 메소드 ver2 phonebook.java

        // 두번째 생성자를 이용하여 객체 생성
        PhoneBook pb2 = new PhoneBook(name, phone);

//        System.out.printf("이름 : %s, 전화번호 : %s, 관계 : %s, 주소 : %s, 메모 : %s %n",
//                pb2.getName(), pb2.getPhone(), pb2.getRelation(), pb2.getAddress(), pb2.getMemo());
//        output(pb2); // 출력 메소드 ver1
        pb2.output();   // 출력 메소드 ver2 phonebook.java

        // 세번째 생성자를 이용하여 객체 생성
        PhoneBook pb3 = new PhoneBook(name, phone, relation, address, memo);

//        System.out.printf("이름 : %s, 전화번호 : %s, 관계 : %s, 주소 : %s, 메모 : %s %n",
//                pb3.getName(), pb3.getPhone(), pb3.getRelation(), pb3.getAddress(), pb3.getMemo());

//        output(pb3);   // 출력 메소드 ver1
        pb3.output();  // 출력 메소드 ver2 phonebook.java
    }


    // 출력 메소드 ver1
    public static void output(PhoneBook phoneBook){
        System.out.printf("이름 : %s, 전화번호 : %s, 관계 : %s, 주소 : %s, 메모 : %s %n",
                phoneBook.getName(), phoneBook.getPhone(), phoneBook.getRelation(), phoneBook.getAddress(), phoneBook.getMemo());
        // call by value임...? 왜
    }
}
