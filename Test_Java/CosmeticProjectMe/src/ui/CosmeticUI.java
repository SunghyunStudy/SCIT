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

    public CosmeticUI() {
        String choice;

        while (true) {
            mainMenu();
            choice = sc.next();
            switch (choice) {
                case "1": inputProduct();break;
                case "2": updateProduct();break;
                case "3": deleteProduct();break;
                case "4": searchProduct();break;
                case "5": productPrint();break;
                case "0": System.out.println("## 프로그램을 종료합니다.");sc.close();return;
                default: System.out.println("## 다시 선택해주세요.");sc.nextLine();
            }
        }
    }

    public void mainMenu(){
        System.out.print("===[ 화장품 관리 ]===\n" +
                         "   1. 상품 등록\n" +
                         "   2. 상품 수정\n" +
                         "   3. 상품 삭제\n" +
                         "   4. 상품 조회\n" +
                         "   5. 전체 상품 조회\n" +
                         "   0. 종 료\n" +
                         "====================\n");
        System.out.print("     > 선택 : ");
    }

    public String subMenu(){
        String type = null;

        System.out.println("<< 상품 등록 >>");
        System.out.print("1. 립스틱 / 2. 파운데이션 : "); type = sc.next();

        if(!(type.equals("1")) && !(type.equals("2"))){
            System.out.println("상품번호를 잘못 입력하였습니다.");
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


        switch(type){
            case "1" : product = makeLipstick(product); break;
            case "2" : product = makeFoundation(product); break;
            default:
                System.out.println("제품타입을 잘못 선택하셨습니다."); return;
        }
        product.setProductNo(productNo);
        boolean result = service.insert(product);

        if(result) System.out.println("## 상품등록이 완료되었습니다.");
        else System.out.println("## 상품등록에 실패했습니다.");

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



    public void searchProduct(){
        String productNo;

        System.out.println("## 조회할 상품번호를 입력하시오 : ");
        productNo = sc.next();

        Product p = service.selectOne(productNo);
        if(p == null){
            System.out.println("## 존재하지 않는 상품번호입니다.");
        }
        else System.out.println(p);
    }


    public void updateProduct(){
        Product p = new Product();
        String productNo;
        String name;
        int price;
        String type;
        String color;

        System.out.println("<< 상품 정보 수정 >>");
        System.out.println("> 수정할 상품의 번호 : "); productNo = sc.next();

        p = service.selectOne(productNo);
        if(p == null){
            System.out.println("## 존재하지 않는 상품입니다.");
            return;
        }

        System.out.println("상품명 : "); name = sc.next();
        System.out.println("상품가격 : "); price = sc.nextInt();

        if(productNo.equals("1")){
            System.out.print("> 립스틱 타입: (1. 립밤 / 2. 립글로스 / 3. 틴트) : "); type = sc.next();
            System.out.print("> 립스틱 색상: (1. 빨강 / 2. 분홍 / 3. 오렌지) : "); color = sc.next();
            Lipstick l = new Lipstick(productNo, name, price, type, color);
            service.update(l);
        }
        else if(productNo.equals("2")){
            String texture;
            System.out.print("> 파운데이션 제형: (1. 크림 / 2. 스틱) : "); texture = sc.next();
            Foundation f = new Foundation(productNo, name, price, texture);
            service.update(f);
        }
    }


    public void deleteProduct(){}


    public void productPrint(){
        Product[] listAll = service.selectAll();
        int count = service.getCount();

        if(count == 0){
            System.out.println("## 등록된 제품이 없습니다.");
            return;
        }

        for(int i = 0; i < listAll.length; i++){
            Product p = listAll[i];
            System.out.println(p);
        }
    }
}
