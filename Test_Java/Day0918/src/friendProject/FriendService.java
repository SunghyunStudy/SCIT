package friendProject;

import java.util.Scanner;

public class FriendService {
    private  Friend friend;
    Scanner sc = new Scanner(System.in);

    // test에서 호출한 FriendService가 바로 실행되는 곳.
    public FriendService(){
        String choice;

        while(true){
            menu();
            choice = sc.next();

            switch(choice){
                case "1" : create();    break;      // Friend 객체 생성
                case "2" : retrieve();  break;      // Friend 객체 출력
                case "3" : update();    break;      // Friend 객체 내용 수정
                case "4" : delete();    break;      // Friend 객체의 참조값을 없앰.
                case "0" :
                    System.out.println("## 프로그램을 종료합니다.");
                    return;         // = System.exit(0)
                default :
                    System.out.println("err) 다시 선택하십시오");
                    sc.nextLine();
            }
        }
    }

    // 메뉴
    public void menu() {
        System.out.println("---------[메인 메뉴]---------");
        System.out.println("         1. 입  력");
        System.out.println("         2. 조  회");
        System.out.println("         3. 수  정");
        System.out.println("         4. 삭  제");
        System.out.println("         0. 종  료");
        System.out.println("-----------------------------");
        System.out.print  ("       메뉴 입력 > ");
    }

    // 입력 메서드
    public void create(){
        System.out.println("\n----[[[[[ 입  력 ]]]]]-----");
        String name;
        int age;
        String type;

        if(friend != null) { // 친구가 없는 상태라면
            System.out.println("## 이미 등록된 친구가 있어서 새롭게 등록할 수 없습니다.");
            return;
        }

        System.out.print("*** 이름 : "); name = sc.next();
        System.out.print("*** 나이 : "); age = sc.nextInt();
        System.out.print("*** 성격(외향적 : t, 내향적 : f) : "); type = sc.next();


        // 문자열은 boolean이 아니므로 변화하는 작업이 필요
        // casting : boolean은 캐스팅 불가능 // int -> double, double -> int와 같이 숫자타입의 기초자료형만 casting 가능.
        // 기본적으로 객체는 못함... 하지만 상속관계를 가진 객체는 캐스팅 가능.

        // 문자열을 boolean으로 바꾸는 방법
        // parsing 하거나 if문을 씀.

        boolean mytype = false;
        // if 방법 1
//        if(type.equals("t")) mytype = true;       // ****** 객체는 == 으로 비교할 수 없음 ******** / 값이 아니라 주소가 같은지 비교하는 연산자임.
//        else mytype = false;

        // if 방법 2
//        mytype = (type.equals("t")) ? true : false;

        friend = new Friend(name, age, (type.equals("t")) ? true : false);

// 아래는 setter로 값 넣는 법임.
//        friend.setName(name);
//        friend.setAge(age);
//        friend.setType(type);
    }


    // 출력(조회) 메서드
    public void retrieve(){
        System.out.println("\n----[[[[[ 조  회 ]]]]]-----");

        if(friend == null){
            System.out.println("등록된 친구정보가 없습니다.");
            return;
        }
        friend.output();
        System.out.println();
    }

    // 수정 메서드
    public void update(){
        System.out.println("\n----[[[[[ 수  정 ]]]]]-----");

        String name;
        int age;
        String type;

        if(friend != null) { // 친구가 없는 상태라면
            System.out.println("## 등록된 친구가 없습니다. \n");
            return;
        }

        friend.output();

        System.out.print("*** 나이 : "); age = sc.nextInt();
        System.out.print("*** 성격(외향적 : t, 내향적 : f) : "); type = sc.next();

        // 객체의 정보 수정
        friend.setAge(age);
        friend.setType((type.equals("t") ? true : false));

        System.out.println("## 수정이 완료되었습니다.");
    }


    // 삭제 메서드
    public void delete(){
        System.out.println("\n----[[[[[ 삭  제 ]]]]]-----");
        String confirm;

        if(friend == null){
            System.out.println("등록된 친구정보가 없습니다.");
            return;
        }

        friend.output();
        System.out.print("정말로 삭제하시겠습니까? (y/n)"); confirm = sc.next();
        if(confirm.equals("Y") || confirm.equals("y")){
            friend = null;
            System.out.println("## 삭제가 완료 되었습니다.");
            return;
        }
        System.out.println("## 삭제 처리가 취소되었습니다.");
    }
}
