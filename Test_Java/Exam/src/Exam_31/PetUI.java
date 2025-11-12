package Exam_31;

import java.util.Scanner;

public class PetUI {
    Scanner sc = new Scanner(System.in);
    PetService service = new PetService();

    public PetUI(){
        while (true){
            String choice = menu();
            switch (choice){
                case "1" : regist(); break;
                case "2" : output(); break;
                case "3" : System.exit(1);
                default:
                    System.out.println("없는 메뉴입니다."); break;
            }


        }
    }

    public String menu(){
        String choice;
        System.out.print("""
                ===== PetShop =====
                1.애완동물등록
                2.전체출력
                3.프로그램종료
                ===================
                선택 : """);
        choice = sc.next();
        return choice;
    }

    public void regist(){
        String name, type;
        int age;

        System.out.print("이름을 입력 해주세요 : "); name = sc.next();
        System.out.print("종류를 입력 해주세요 : "); type = sc.next();
        System.out.print("나이를 입력 해주세요 : "); age = sc.nextInt(); sc.nextLine();

        PetVo pet = new PetVo(name, type, age);

        if(service.insert(pet)){
            System.out.println("등록을 완료했습니다.");
        }
    }

    public void output(){
        PetVo[] list = service.search();
        if(service.count == 0){
            System.out.println("조회 가능한 정보가 없습니다.");
            return;
        }

        for(int i = 0; i < service.count(); i++){
            System.out.println(list[i]);
        }
    }


}
