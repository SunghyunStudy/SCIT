package fitness.ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fitness.service.FitnessService;
import fitness.service.FitnessServiceImpl;
import fitness.vo.Fitness;

public class FitnessUI {
	Scanner keyin = new Scanner(System.in);
	FitnessService service = new FitnessServiceImpl();
	
	Fitness fitness = null;
	
	public FitnessUI() {
		String choice;
		
		while(true) {
			menu();
			choice = keyin.next();
			
			switch(choice) {
			case "1" : create();   break;
			case "2" : retrieve(); break;
			case "3" : update();   break;
			case "4" : delete();   break;
			case "5" : retrieveAll();   break;
			case "0" : 
				System.out.println("## 프로그램을 종료합니다.");
                keyin.close();
				return;
			default :
				System.out.println("err>> 메뉴를 다시 선택하세요");
			}
		}
	} // 생성자 끝
	
	/**
	 * 회원 가입
	 * 
	 */
	private void create() {
		System.out.println("\n<< 회원 가입 >>");
		
		String id;
		String name, g;
        boolean gender;
		double height, weight;
		
		System.out.print("> 아이디: ");
		id = keyin.next();
		
		// 이 아이디에 해당하는 회원)이 존재하는지 목록에서 확인하는 작업
		// 회원 정보가 있다. ==> exception(x) ==> 가입 불가능
		// 회원 정보가 없다. ==> exception ==> 가입이 가능
		Fitness fitness = null;
		try {
			fitness = service.retrieve(id);
			
			System.out.println("## 회원정보가 존재합니다.");
			return;
			
		} catch (Exception e) {  // 회원이 없어야(즉, Exception 상태여야) 데이터 삽입이 가능
			System.out.print("> 이  름: ");
			name = keyin.next();
			
			System.out.print("> 성별(M/F): ");
			g = keyin.next();
            if(!(g.equalsIgnoreCase("M") || (g.equalsIgnoreCase("F")))) {
                System.out.println("성별은 'M'이나 'F'로 입력해주세요");
                return;
            }
            gender = (g.equalsIgnoreCase("M")) ? true : false;
			
			System.out.print("> 키(cm): ");
			height = keyin.nextDouble();
			
			System.out.print("> 몸무게(kg): ");
			weight = keyin.nextDouble();

			fitness = new Fitness(id, name, gender, height, weight);
			
			try {
				service.create(fitness);
				System.out.println("## 회원 가입이 완료되었습니다.\n");
			} catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		} 
	}
	
	/**
	 * 회원 정보 조회
	 */
	private void retrieve() {
		System.out.println("\n<< 회원 정보 조회 >>");
		
		String id;
		
		System.out.print("> 아이디: ");
		id = keyin.next();
		
		// 이 아이디에 해당하는 회원)이 존재하는지 목록에서 확인하는 작업
		Fitness fitness = null;
		
		try {
			fitness = service.retrieve(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
				
		// 출력
		System.out.println(fitness);
		System.out.println();
	}
	
	/**
	 * 회원 탈퇴
	 */
	private void delete() {
		System.out.println("\n<< 회원 탈퇴 >>");
		
		String id;
		String check;
		
		System.out.print("> 아이디: ");
		id = keyin.next();
		
		System.out.print("## 탈퇴하시겠습니까? (y/n)");
		check = keyin.next();
		
		if(!(check.equals("Y") || check.equals("y") )) {
			System.out.println("## 탈퇴 작업이 중단되었습니다.\n");
			return;
		}
		
		try {
			service.delete(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		 
		System.out.println("## 탈퇴 완료되었습니다.\n");
	}
	
	/**
	 * 회원 정보 수정
	 */
	private void update() {
		System.out.println("\n<< 회원 정보 수정 >>");
		
		String id;
		String check;
		double height, weight;
		
		System.out.print("> 아이디: ");
		id = keyin.next();
		
		Fitness fitness = null;
		
		try {
			fitness = service.retrieve(id); 
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.print("> 키(cm): ");
		height = keyin.nextDouble();
		
		System.out.print("> 몸무게(kg): ");
		weight = keyin.nextDouble();
		
		System.out.print("## 수정하시겠습니까? (y/n)");
		check = keyin.next();
		
		if(!(check.equals("Y") || check.equals("y") )) {
			System.out.println("## 수정 작업이 중단되었습니다.\n");
			return;
		}
		
		// 이전 정보의 데이터를 수정
		fitness.setWeight(weight);
		fitness.setHeight(height);
		
		try {
			service.update(fitness);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		
		System.out.println("## 수정 완료되었습니다.\n");
	}
	
	/**
	 * service 클래스에게 회원 목록을 전달받아 화면에 출력
	 */
	private void retrieveAll() {
        String subChoice;

        // 회원 목록 전체 조회
        List<Fitness> list = service.retrieveAll();
        int count = list.size();

        if (count == 0) {
            System.out.println("## 가입된 회원이 없습니다.\n");
            return;
        }

        while (true) {
            System.out.print("1. 아이디순  2. 이름순  0. 이전 화면으로 ==> ");
            subChoice = keyin.next();

            switch (subChoice) {
                case "1":
                    printId(list);
                    break;
                case "2":
                    printName(list);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("err>> 메뉴를 다시 선택하세요");
            }

            System.out.println("** 가입된 회원수: " + count + "명");
            System.out.println();
        }
    }

    /**
     * 화면에 출력되는 메인 메뉴
     */
    private void menu () {
        System.out.println("==== [ 피트니스 회원 관리 ] ====");
        System.out.println("        1) 회원 가입");
        System.out.println("        2) 정보 조회");
        System.out.println("        3) 정보 수정");
        System.out.println("        4) 탈     퇴");
        System.out.println("        5) 전체 회원 조회");
        System.out.println("        0) 종     료");
        System.out.println("--------------------------------");
        System.out.print("          >> 선택: ");
    }

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
}