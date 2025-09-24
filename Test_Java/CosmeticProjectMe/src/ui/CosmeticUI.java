package ui;

/**
 * 사용자가 키보드로 데이터를 입력
 * 실행 결과를 사용자가 볼 수 있도록 출력
 *
 */


import service.CosmeticService;
import service.CosmeticServiceImpl;
import vo.Foundation;
import vo.Lipstick;
import vo.Product;

import java.util.Scanner;


public class CosmeticUI {
    Scanner sc = new Scanner(System.in);
    CosmeticService service = new CosmeticServiceImpl();
    // CosmeticService(부모) --업케스팅-->   CosmeticServiceImpl(자식)

    public CosmeticUI() {}

    public void mainMenu(){
        String choice;

        System.out.print("===[ 화장품 관리 ]===\n" +
                         "   1. 상품 등록\n" +
                         "   2. 상품 수정\n" +
                         "   3. 상품 삭제\n" +
                         "   4. 상품 조회\n" +
                         "   5. 전체 상품 조회\n" +
                         "   0. 종 료\n" +
                         "====================%n");
        System.out.print("     > 선택 : "); choice = sc.next();

        switch(choice){
            case "1" : inputProduct(); break;
            case "2" : updateProduct(); break;
            case "3" : deleteProduct(); break;
            case "4" : searchProduct(); break;
            case "5" : productPrint(); break;
            case "0" :
                System.out.println("## 프로그램을 종료합니다."); sc.close(); return;
            default:
                System.out.println("## 다시 선택해주세요."); sc.nextLine();
        }
    }

    public String subMenu(){
        String type = null;

        System.out.println("<< 상품 등록 >>");
        System.out.print("1. 립스틱 / 2. 파운데이션 : "); type = sc.next();

        if(!(type == "1") && !(type == "2")){
            return null;
        }

        return type;
    }

    public void inputProduct(){
        Product product = new Product();
        String productNo;
        String type;

        type = subMenu();

        System.out.println("> 상품번호 : "); productNo = sc.next();

        // 있는 번호인지 확인
        product = service.selectOne(productNo);
        if(product != null){
            System.out.println("## 이미 등록된 상품입니다.");
            return;
        }

        product = makeProduct();
        product.setProductNo(productNo);

        switch(type){
            case "1" : makeLipstick(product); break;
            case "2" : makeFoundation(product); break;
            default:
                System.out.println("제품타입을 잘못 선택하셨습니다."); return;
        }
    }

    public Product makeProduct(){

        String name;
        int price;

        System.out.println("상품명 : "); name = sc.next();
        System.out.println("상품가격 : "); price = sc.nextInt();

        Product product = new Product(null, name, price);
        return product;

    }

    public Lipstick makeLipstick(Product product){
        String type;
        String color;

        System.out.print("> 립스틱 타입: (1. 립밤 / 2. 립글로스 / 3. 틴트) : "); type = sc.next();
        System.out.print("> 립스틱 색상: (1. 빨강 / 2. 분홍 / 3. 오렌지) : "); color = sc.next();

        Lipstick lipstick = new Lipstick(product.getProductNo(), product.getName(), product.getPrice(), type, color);
        return lipstick;
    }

    public Foundation makeFoundation(Product product){
        String texture;

        System.out.print("> 파운데이션 제형: (1. 크림 / 2. 스틱) : "); texture = sc.next();

        Foundation foundation = new Foundation(product.getProductNo(), product.getName(), product.getPrice(), texture);
        return foundation;
    }

    public void searchProduct(){}

    public void updateProduct(){}

    public void deleteProduct(){}

    public void productPrint(){}



}
