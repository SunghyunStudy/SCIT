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
    Scanner keyin = new Scanner(System.in);
    CosmeticService service = new CosmeticServiceImpl();
    // CosmeticService(부모) --업케스팅-->   CosmeticServiceImpl(자식)

    public CosmeticUI() {
        String choice;

        while(true){
            mainMenu();
            choice = keyin.next();

            switch(choice){
                case "1" : inputProduct(); break;        // 상품 생성
                case "2" : updateProduct(); break;
                case "3" : deleteProduct(); break;
                case "4" : searchProduct(); break;
                case "5" : productPrint(); break;
                case "0" :
                    System.out.println("프로그램을 종료합니다.."); keyin.close(); return;
                default :
                    System.out.println("메뉴를 다시 선택해주세요."); keyin.nextLine(); // keyin.nextLine();는 잘못입력해서 버퍼에 입력값이 남아있는걸 지워버림.
            }
        }
    }

    /**
     * 메인 메뉴
     */
    public void mainMenu(){
        System.out.print("===[ 화장품 관리 ]===\n" +
                         "   1. 상품 등록\n" +
                         "   2. 상품 수정\n" +
                         "   3. 상품 삭제\n" +
                         "   4. 상품 조회\n" +
                         "   5. 전체 상품 조회\n" +
                         "   0. 종 료\n" +
                         "====================\n" +
                         "     > 선택: ");


    }

    public String subMenu(){
        String type;
        System.out.print("1. 립스틱 / 2. 파운데이션 : ");
        type = keyin.next();

        if(!type.equals("1") && !type.equals("2")){
            System.out.println("## 다시 선택해 주세요");
            return null;
        }
        return type;
    }




    /**
     *  상품을 삽입
     *  상위 클래스인 Product를 생성해서 반환
     * @return
     */
    public Product makeProduct(){
        String name;  // 상품명
        int price;

        System.out.print("> 상품명 : "); name = keyin.next();
        System.out.print("> 상품 가격 : "); price = keyin.nextInt();

        Product product = new Product(null, name, price);
        // productNo은 inputProduct에서 set으로 추가.
        return product;

    }


    /**
     * 상품 생성 후 상품 등록.
     * @return
     */
    public void inputProduct(){ // product를 void로 바꿈 어차피 여기서는 반환하는 곳이 없으니까.
        System.out.println("<< 상품 등록 >>");
        Product product = null;
        String productNo;
        String type;

        type = subMenu();

        // 공통 데이터 입력
        System.out.print("> 상품 번호 : "); productNo = keyin.next();  // primary key임 (nn, uq)

        // 상품번호가 존재하는지 확인하는 작업
        Product p = service.selectOne(productNo);
        if(p != null){
            System.out.println("## 이미 등록된 상품입니다.");
            return; // continue의 역할임.
        }


        // 새롭게 등록해야되는 상품인 경우 제품의 넘버, 이름, 가격을 입력받음 (makeProduct()를 통해)
        p = makeProduct();        // PK만 없는 상태
        p.setProductNo(productNo);    // 제품 번호는 makeProduct에서 넣지 못했기 때문에 여기서 추가함. // p = makeProduct();  가 없으면 set이 안되는거임

        // 립스틱, 파운데이션의 경우 고유한 데이터를 추가로 입력받음.
        switch (type){
            case "1" : p = makeLipstick(p);     break;
            case "2" : p = makeFoundation(p);   break;
            default:
                System.out.println("## 다시 선택해 주세요.");
                return;
        }

        // DB에 등록 (여기서는 배열) : 서비스에서 할 수 있음.
        boolean result = service.insert(p);
        if(result) System.out.println("## 상품이 등록되었습니다.");
        else System.out.println("## 등록에 실패했습니다.");
    }


    /**
     * 공통 데이터인 Product를 전달받아 추가적으로 립스틱 정보를 만들어 낸 후
     * 생성된 립스틱을 반환함.
     * @param product
     * @return
     */
    public Lipstick makeLipstick(Product product){
        String type, color;

        System.out.print("> 립스틱 타입: (1. 립밤 / 2. 립글로스 / 3. 틴트) : "); type = keyin.next();
        System.out.print("> 립스틱 색상: (1. 빨강 / 2. 분홍 / 3. 오렌지) : "); color = keyin.next();


        // 공통속성이 포함된 립스틱
        Lipstick lipstick = new Lipstick(product.getProductNo(), product.getName(), product.getPrice(), type, color);
        return lipstick;
    }


    /**
     * 공통 데이터인 Product를 전달받아 추가적으로 파운데이션 정보를 만들어 낸 후
     * 생성된 파운데이션을 반환
     * @param product
     * @return
     */
    public Foundation makeFoundation(Product product){
        String texture;

        System.out.println("> 파운데이션 제형: (1. 크림 / 2. 스틱) : "); texture = keyin.next();

        Foundation foundation = new Foundation(product.getProductNo(), product.getName(), product.getPrice(), texture);
        return foundation;
    }



    public void searchProduct(){}
    public void updateProduct(){}
    public void deleteProduct(){}


    public void productPrint(){
        int count = service.getCount(); // count 메서드를 만든 이유
        Product[] list = service.selectAll();   // selectAll()은 list를 반환함.

        if(count == 0){
            System.out.println("## 등록된 제품이 하나도 없습니다.");
            return;
        }

        for(int i = 0; i < count; ++i){
            // 제품을 출력한다.
            Product p = list[i];        // p 는 립스틱이 될 수도 파운데이션이 될 수도 있음
            System.out.println(p);      // 프린트하면 클래스에서 만든 toString메서드가 자동으로 출력됨
        }
    }



}
