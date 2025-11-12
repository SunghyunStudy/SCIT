package ui;

/**
 * 사용자가 키보드로 데이터를 입력
 * 실행 결과를 사용자가 볼 수 있도록 출력
 */

import service.CosmeticService;
import service.CosmeticServiceImpl;
import vo.Foundation;
import vo.Lipstick;
import vo.Product;

import java.util.Scanner;

public class CosmeticUI {
    Scanner keyin = new Scanner(System.in);
    CosmeticService service = new CosmeticServiceImpl();

    // 생성자
    public CosmeticUI() {
        String choice;

        while(true) {
            mainMenu();
            choice = keyin.next();

            switch(choice) {
                case "1" : makeProduct(); break;   // 상품 생성
                case "2" : updateProduct(); break;
                case "3" : deleteProduct(); break;
                case "4" : searchProduct(); break;
                case "5" : productPrint(); break;
                case "0" :
                    System.out.println("## 프로그램을 종료합니다.");
                    keyin.close();  // 메모리 자원을 반환
                    return;
                default :
                    System.out.println("## 메뉴를 잘못선택했습니다.");
                    keyin.nextLine();
            }
        }
    }

    /**
     * 메인 메뉴
     */
    public void mainMenu() {
        System.out.println("===[ 화장품 관리 ]===");
        System.out.println("  1. 상품 등록");
        System.out.println("  2. 상품 수정");
        System.out.println("  3. 상품 삭제");
        System.out.println("  4. 상품 조회");
        System.out.println("  5. 전체 상품 조회");
        System.out.println("  0. 종 료");
        System.out.println("======================");
        System.out.print  ("   > 선 택: ");
    }

    /**
     * 서브 메뉴
     * @return
     */
    public String subMenu() {
        String type;

        System.out.print("1. 립스틱 / 2. 파운데이션: ");
        type = keyin.next();

        if( !(type.equals("1") || type.equals("2"))) {
            System.out.println("## 다시 선택해 주세요");
            return null;
        }
        return type;
    }

    /**
     * 상품 생성
     * @return
     */
    public void makeProduct() {
        System.out.println("<< 상품 등록 >> ");

        Product product = null;
        String productNo;
        String type;

        type = subMenu();  // 립스틱인지? 파운데이션인지

        // 공통 데이터 입력
        System.out.print("> 상품번호 : "); productNo = keyin.next();  // Primary Key(nn, unique)

        // 상품번호가 존재하는지 확인하는 작업이 필요함!!
        Product p = service.selectOne(productNo);  //

        if(p != null) {
            System.out.println("## 이미 등록된 상품입니다.");
            return;
        }
        // 새롭게 등록해야되는 상품인 경우
        p = insertProduct();       // 공통 데이터 생성
        p.setProductNo(productNo); // PK까지 있는 상태

        // 각 상품의 고유한 데이터를 추가로 입력받는다.
        switch(type) {
            case "1" : p = makeLipstick(p);    break;
            case "2" : p = makeFoundation(p);  break;
            default :
                System.out.println("## 다시 선택해 주세요");
                return;
        }

        // DB에 등록
        boolean result = service.insert(p);
        if(result) System.out.println("## 상품이 등록되었습니다.");
        else System.out.println("## 등록에 실패했습니다.");
    }

    /**
     * 공통속성, 상위 클래스인 Product 생성해서 반환
     */
    public Product insertProduct() {
        String name;   // 상품명
        int price;

        System.out.print("> 상품명: "); name = keyin.next();
        System.out.print("> 상품 가격: "); price = keyin.nextInt();

        Product product = new Product(null, name, price);

        return product;
    }

    /**
     * 공통데이터인 Product를 전달받아 추가적으로 립스틱 정보를 만들어 낸 후
     * 생성된 립스틱을 반환
     * @param product 공통 데이터인 Product
     * @return 생성된 립스틱 객체
     */
    public Lipstick makeLipstick(Product product){
        String type;  // 립스틱의 종류
        String color; // 립스틱 색상

        System.out.print("> 립스틱 타입: (1. 립밤 / 2. 립글로스 / 3. 틴트) => ");
        type = keyin.next();

        System.out.print("> 립스틱 색상: (1. 빨강 / 2. 분홍 / 3. 오렌지) => ");
        color = keyin.next();

        // 공통속성이 포함된 립스틱
        Lipstick lipstick = new Lipstick(
                product.getProductNo(), product.getName(), product.getPrice(),
                type, color);

        return lipstick;
    }

    /**
     * 공통데이터인 Product를 전달받아 추가적으로 파운데이션 정보를 만들어 낸 후
     * 생성된 파운데이션을 반환
     * @param product 공통 데이터인 Product
     * @return 생성된 파운데이션 객체
     */
    public Foundation makeFoundation(Product product){
        String texture;  //

        System.out.print("> 파운데이션 제형: (1. 크림 / 2. 스틱) => ");
        texture = keyin.next();

        // 공통속성이 포함된 파운데이션
        Foundation foundation = new Foundation(
                product.getProductNo(), product.getName(), product.getPrice(),
                texture);

        return foundation;
    }

    // 하나의 상품을 출력
    public void searchProduct() {
        System.out.println("\n<< 상품 출력 >>");

        String productNo;
        System.out.print("> 상품번호: ");
        productNo = keyin.next();

        Product product = service.selectOne(productNo);

        if(product == null) {
            System.out.println("## 등록된 상품이 없습니다.");
            return;
        }

        System.out.println(product);
    }

    public void updateProduct() {
        System.out.println("\n<< 상품 정보 수정 >>");

        System.out.print("## 수정할 상품의 번호: ");
        String productNo = keyin.next();
        Product product = null;

        product = service.selectOne(productNo);

        if(product == null) {
            System.out.println("## 상품이 존재하지 않습니다.\n");
            return;
        }

        System.out.println(product);

        Product p = insertProduct();
        p.setProductNo(productNo);

        if(product instanceof Lipstick)
            product = makeLipstick(p);
        else
            product = makeFoundation(p);

        System.out.println(product);
        service.update(product);

        System.out.println("## 수정이 완료되었습니다.\n");
    }

    public void deleteProduct() {
        System.out.println("\n<< 상품 삭제 >>");

        System.out.print("## 삭제할 상품의 번호: ");
        String productNo = keyin.next();
        Product product = null;
        String confirm = null;

        product = service.selectOne(productNo);

        if(product == null) {
            System.out.println("## 상품이 존재하지 않습니다.");
            return;
        }

        System.out.println(product);

        System.out.println("## 상품을 삭제하시겠습니까? (y/n) ");
        confirm = keyin.next();

        if(confirm.equals("Y") || confirm.equals("y")) {
            service.delete(productNo);
            System.out.println("## 상품이 삭제되었습니다.\n");
            return ;
        }
        System.out.println("## 삭제작업이 취소되었습니다.\n");

    }

    /**
     * 현재 등록된 모든 제품을 출력하는 메소드
     */
    public void productPrint() {
        int count = service.getCount();
        Product[] list = service.selectAll();

        if(count == 0) {
            System.out.println("## 등록된 제품이 하나도 없습니다.");
            return ;
        }

        for(int i=0; i<count; ++i)
            System.out.println(list[i]);

    }
}
