package ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import service.FitnessService;
import service.FitnessServiceImpl;
import vo.Fitness;
import vo.MaleMember;
import vo.FemaleMember;

public class FitnessUI {

    // 키보드 입력을 받기 위한 Scanner 객체
    private Scanner keyin = new Scanner(System.in);

    // 비즈니스 로직을 처리할 Service 객체
    private FitnessService service = new FitnessServiceImpl();

    /**
     * 생성자: 프로그램의 메인 루프(반복문) 역할을 합니다.
     */
    public FitnessUI() {
        while (true) {
            mainMenu(); // 메인 메뉴 출력
            String choice = keyin.next(); // 사용자 선택 입력

            switch (choice) {
                case "1": create(); break;
                case "2": retrieve(); break;
                case "3": update(); break;
                case "4": delete(); break;
                case "5": retrieveAll(); break;
                case "0":
                    System.out.println("## 프로그램을 종료합니다.");
                    keyin.close(); // Scanner 자원 해제
                    return; // 프로그램 종료
                default:
                    System.out.println("err>> 메뉴를 다시 선택하세요.");
            }
        }
    }

    /**
     * 1. 신규 회원 가입
     */
    private void create() {
        System.out.println("\n<< 1. 신규 회원 가입 >>");

        System.out.print("> 아이디: ");
        String id = keyin.next();

        // 아이디 중복 검사
        if (service.retrieve(id) != null) {
            System.out.println("## 이미 사용 중인 아이디입니다. 가입할 수 없습니다.\n");
            return;
        }

        System.out.print("> 이름: ");
        String name = keyin.next();

        System.out.print("> 성별(M/F): ");
        String genderInput = keyin.next();
        if (!genderInput.equalsIgnoreCase("M") && !genderInput.equalsIgnoreCase("F")) {
            System.out.println("err>> 성별은 'M' 또는 'F'로 입력해야 합니다.\n");
            return;
        }

        System.out.print("> 키(cm): ");
        double height = keyin.nextDouble();

        System.out.print("> 몸무게(kg): ");
        double weight = keyin.nextDouble();

        System.out.print("> VIP 회원이신가요? (Y/N): ");
        String isVipInput = keyin.next();

        String trainerName = null; // 기본은 일반 회원(null)
        if (isVipInput.equalsIgnoreCase("Y")) {
            System.out.print("> 담당 트레이너 이름: ");
            trainerName = keyin.next();
        }

        Fitness newMember = null;

        // 입력된 성별에 따라 MaleMember 또는 FemaleMember 객체를 생성
        if (genderInput.equalsIgnoreCase("M")) {
            newMember = new MaleMember(id, name, height, weight, trainerName);
        } else {
            newMember = new FemaleMember(id, name, height, weight, trainerName);
        }

        // Service에 회원 생성을 요청하고 결과에 따라 메시지 출력
        if (service.create(newMember)) {
            System.out.println("## 회원 가입이 완료되었습니다. 🎉\n");
        } else {
            // 이 경우는 ID 중복 검사를 통과했으므로 거의 발생하지 않음
            System.out.println("err>> 알 수 없는 오류로 가입에 실패했습니다.\n");
        }
    }

    /**
     * 2. 회원 정보 조회
     */
    private void retrieve() {
        System.out.println("\n<< 2. 회원 정보 조회 >>");
        System.out.print("> 조회할 아이디: ");
        String id = keyin.next();

        Fitness fitness = service.retrieve(id);

        if (fitness == null) {
            System.out.println("## 해당 ID의 회원이 존재하지 않습니다.\n");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            // 다형성에 의해 MaleMember 또는 FemaleMember의 toString()이 알맞게 호출됨
            System.out.println(fitness);
            System.out.println("---------------------------------------------------------------------------------------------------------\n");
        }
    }

    /**
     * 3. 회원 정보 수정 (키, 몸무게)
     */
    private void update() {
        System.out.println("\n<< 3. 회원 정보 수정 >>");
        System.out.print("> 수정할 아이디: ");
        String id = keyin.next();

        Fitness fitness = service.retrieve(id);

        if (fitness == null) {
            System.out.println("## 해당 ID의 회원이 존재하지 않아 수정할 수 없습니다.\n");
            return;
        }

        System.out.printf("> 현재 정보: 키 %.2fcm, 몸무게 %.2fkg\n", fitness.getHeight(), fitness.getWeight());

        System.out.print("> 수정할 키(cm): ");
        double newHeight = keyin.nextDouble();

        System.out.print("> 수정할 몸무게(kg): ");
        double newWeight = keyin.nextDouble();

        System.out.print("## 정말 수정하시겠습니까? (y/n): ");
        String confirm = keyin.next();

        if (confirm.equalsIgnoreCase("y")) {
            // 수정할 정보를 담기 위한 임시 객체 생성. ID, 키, 몸무게 외 정보는 중요하지 않음.
            Fitness updatedInfo = new MaleMember(id, null, newHeight, newWeight, null);

            if (service.update(updatedInfo)) {
                System.out.println("## 정보 수정이 완료되었습니다.\n");
            } else {
                System.out.println("err>> 정보 수정에 실패했습니다.\n");
            }
        } else {
            System.out.println("## 수정 작업이 취소되었습니다.\n");
        }
    }

    /**
     * 4. 회원 탈퇴
     */
    private void delete() {
        System.out.println("\n<< 4. 회원 탈퇴 >>");
        System.out.print("> 탈퇴할 아이디: ");
        String id = keyin.next();

        // 회원이 존재하는지 먼저 확인
        if (service.retrieve(id) == null) {
            System.out.println("## 해당 ID의 회원이 존재하지 않습니다.\n");
            return;
        }

        System.out.print("## 정말 탈퇴하시겠습니까? (y/n): ");
        String confirm = keyin.next();

        if (confirm.equalsIgnoreCase("y")) {
            if (service.delete(id)) {
                System.out.println("## 회원 탈퇴가 완료되었습니다.\n");
            } else {
                // 이 경우도 회원이 존재함을 확인했으므로 거의 발생하지 않음
                System.out.println("err>> 탈퇴 처리에 실패했습니다.\n");
            }
        } else {
            System.out.println("## 탈퇴 작업이 취소되었습니다.\n");
        }
    }

    /**
     * 5. 전체 회원 목록 조회
     */
    private void retrieveAll() {
        System.out.println("\n<< 5. 전체 회원 목록 >>");
        List<Fitness> list = service.retrieveAll();

        if (list.isEmpty()) {
            System.out.println("## 가입된 회원이 없습니다.\n");
            return;
        }

        while (true) {
            System.out.print("1. 아이디순 정렬  2. 이름순 정렬  0. 이전 메뉴 ==> ");
            String subChoice = keyin.next();

            switch (subChoice) {
                case "1":
                    printId(list);
                    break;
                case "2":
                    printName(list);
                    break;
                case "0":
                    return; // 이전 메뉴로 복귀
                default:
                    System.out.println("err>> 메뉴를 다시 선택하세요.");
            }
        }
    }

    /**
     * 리스트를 형식에 맞게 화면에 출력하는 도우미 메소드
     */
    // 아이디순 출력 (Fitness가 가지고 있는 compareTo() 메소드 이용
    private static void printId (List <Fitness> list) {
        list.sort((x, y) -> x.compareTo(y));
        list.forEach(x -> System.out.println(x));

        System.out.println();
    }

    // 이름순 출력 (Collections 메소드의 sort() 메소드를 이용
    private static void printName (List < Fitness > list) {
        Collections.sort(list, (x, y) -> x.getName().compareTo(y.getName()) > 1 ? 1
                : x.getName().compareTo(y.getName()) < 1 ? -1 : 0);
        list.forEach(x -> System.out.println(x));

        System.out.println();
    }
    /**
     * 메인 메뉴를 화면에 출력
     */
    private void mainMenu() {
        System.out.println("======= [ 피트니스 회원 관리 ] =======");
        System.out.println("         1. 회원 가입");
        System.out.println("         2. 정보 조회");
        System.out.println("         3. 정보 수정");
        System.out.println("         4. 회원 탈퇴");
        System.out.println("         5. 전체 회원 조회");
        System.out.println("         0. 프로그램 종료");
        System.out.println("------------------------------------");
        System.out.print("           >> 선택: ");
    }
}