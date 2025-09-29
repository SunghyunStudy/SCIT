package Exam_30;

import java.util.Scanner;

public class Service {
    Car[] carList = new Car[1000];
    Car car = new Car();
    int count = 0;
    Scanner sc = new Scanner(System.in);
    String choice;

    public Service(){
        while(true){
            choice = menu();
            switch (choice){
                case "1" : reserve();
                    System.out.println("예약이 완료되었습니다."); break;
                case "2" : car = selectOne(selectPhoneNo());
                            if(car != null){
                                System.out.println(car);
                            }else System.out.println("검색결과가 없습니다."); break;
                case "3" : carList = selectAll(); for(int i = 0; i < count; i++) {
                    System.out.println(carList[i]);} break;
                case "4" :
                    System.out.println("프로그램을 종료합니다."); return;
                default:
                    System.out.println("잘못된 메뉴를 선택하셨습니다."); break;
            }
        }
    }



    public String menu(){
        System.out.print("===================\n" +
                           "1.예약\n" +
                           "2.조회\n" +
                           "3.전체출력\n" +
                           "4.프로그램종료\n" +
                           "===================\n" +
                           "선택 : ");
        return sc.next();
    };


    public boolean reserve(){
        String name, carNo, phoneNo, location;
        if(count >= 1000) return false;

        System.out.print("이름을 입력 : "); name = sc.next();
        System.out.print("차번호를 입력 : "); carNo = sc.next();
        phoneNo = selectPhoneNo();
        System.out.print("위치를 입력 : "); location = sc.next();

        car = new Car(name, carNo, phoneNo, location);
        carList[count++] = car;
        return true;
    }

    public String selectPhoneNo(){
        String phoneNo;
        System.out.print("전화번호를 입력 : ");
        phoneNo = sc.next();
        return phoneNo;
    }

    public Car selectOne(String phoneNo){
        for(int i = 0; i < count; i++){
            if(carList[i].getPhoneNo().equals(phoneNo)){
                return carList[i];
            }
        }
        return null;
    }

    public Car[] selectAll(){
        return carList;
    }
}
