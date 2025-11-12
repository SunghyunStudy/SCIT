package pet.ui;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import pet.service.PetHospitalService;
import pet.service.PetHospitalServiceImpl;
import pet.vo.Bird;
import pet.vo.Mammal;
import pet.vo.Pet;

public class PetHospitalUI {
	Scanner keyin = new Scanner(System.in);
    PetHospitalService service = new PetHospitalServiceImpl();

    Pet pet = null;
	
	public PetHospitalUI() {
		String choice;
		
		while(true) {
			menu();
			choice = keyin.next();
			
			switch(choice) {
			case "1" : create(); break;
			case "2" : retrieve();   break;
			case "3" : delete();   break;
			case "4" : retrieveAll();   break;
			case "0" :
                System.out.println("프로그램을 종료합니다."); keyin.close(); return;
             default :
                 System.out.println("메뉴를 다시 선택하십시오");
			}
		}
	} // 생성자 끝
	
	/**
	 * 애완동물 등록
	 * 
	 */
	private void create() {
        System.out.println("\n<< 애완동물 등록 >>");

        String choice;
        String id;
        int age;
        String name, furColor, breed, species;

        Pet pet = null;

        // 이 아이디에 해당하는 애완동물이 존재하는지 목록에서 확인하는 작업
        System.out.print(">> 애완동물의 종류: 1) 강아지, 고양이  2) 새 : ");
        choice = keyin.next();

        if(!choice.equals("1") && !choice.equals("2")){
            System.out.println("애완동물의 종류를 잘못 입력했습니다.");
            return;
        }

        System.out.print("> 아이디: ");
        id = keyin.next();

        if (service.retrieve(id) != null) {
            System.out.println("## 애완동물 정보가 존재합니다.");
            return;
        }


        // Code Here
        System.out.println("> 이름: "); name = keyin.next();
        keyin.nextLine();
        System.out.println("> 나이: "); age = keyin.nextInt();


        if(choice.equals("1")){

            System.out.println("> 털색: "); furColor = keyin.next();
            keyin.nextLine();

            System.out.println("> 종류: "); breed = keyin.next();
            keyin.nextLine();

            pet = new Mammal(id, name, age, furColor, breed);

        }
        else if(choice.equals("2")){
            System.out.println("> 새 종류"); species = keyin.next();
            keyin.nextLine();

            pet = new Bird(id, name, age, species);

        }


        service.create(pet);
        System.out.println(service.retrieve(id));
        System.out.println("## 등록 완료되었습니다.\n");
	}




	/**
	 * 애완동물 정보 조회
	 */
	private void retrieve() {
        System.out.println("\n<< 애완동물 조회 >>");

        String id;

        // code here

        System.out.println("> 아이디:");
        id = keyin.next();
        keyin.nextLine();

        Pet temp = service.retrieve(id);

        if(temp == null){
            System.out.println("## 등록된 애완동물의 정보가 없습니다.");
            return;
        }
        else{
            System.out.println(temp);
        }
	}
	
	/**
	 * 애완동물 정보 삭제
	 */
	private void delete() {
        System.out.println("\n<< 애완동물 정보 삭제 >>");

        String id;
        String check;       // 삭제 여부를 묻기 위한 변수
        
        
        // Code

        System.out.println("> 아이디: "); id = keyin.next();
        keyin.nextLine();

        Pet p = service.retrieve(id);

        if(p == null){
            System.out.println("## 등록된 애완동물의 정보가 없습니다.");
            return;
        }

        System.out.println(p);
        System.out.println("## 삭제하시겠습니까? (y/n)"); check = keyin.next();
        keyin.nextLine();

        if(check.equalsIgnoreCase("Y")){
            service.delete(id);
        }
        else if(check.equalsIgnoreCase("N")){
            System.out.println("삭제를 취소하였습니다.");
            return;
        }
        else if(!check.equalsIgnoreCase("Y") && !check.equalsIgnoreCase("N")){
            System.out.println("잘못된 메뉴를 입력하였습니다.");
            return;
        }

        System.out.println("## 삭제 완료되었습니다.\n");
 
	}
	
	/**
	 * service 클래스에게 애완동물의 목록을 전달받아 화면에 출력
	 */
	private void retrieveAll() {
        String subChoice;

        // 애완동물 목록 전체 조회
        List<Pet> list = service.retrieveAll();
        int count = 0; // 등록된 애완동물 수를 출력하기 위한 변수;

        // Code here

        count = list.size();


        if (count == 0) {
            System.out.println("## 등록된 애완동물이 없습니다.\n");
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

            System.out.println("** 등록된 애완동물 수: " + count + "마리");
            System.out.println();
        }
    }

    /**
     * 화면에 출력되는 메인 메뉴
     */
    private void menu () {
        System.out.println("==== [ 애완동물 병원 관리 ] ====");
        System.out.println("      1) 애완동물 등록");
        System.out.println("      2) 애완동물 조회");
        System.out.println("      3) 애완동물 삭제");
        System.out.println("      4) 애완동물 전체 조회");
        System.out.println("      0) 종     료");
        System.out.println("--------------------------------");
        System.out.print  ("        >> 선택: ");
    }

    // 아이디순 출력 (Pet이 가지고 있는 compareTo() 메소드 이용
    private static void printId (List<Pet> list) {
        // Code Here
        list.sort((x, y) -> x.compareTo(y));
        list.forEach(x -> System.out.println(x));

        System.out.println();
    }

    // 이름순 출력 (Collections 메소드의 sort() 메소드를 이용
    private static void printName (List < Pet > list) {
        // Code Here
        Collections.sort(list, (x, y) -> x.getName().compareTo(y.getName()) > 1 ? 1
                : x.getName().compareTo(y.getName()) < 1 ? -1 : 0);
        list.forEach(x -> System.out.println(x));

        System.out.println();
    }

}