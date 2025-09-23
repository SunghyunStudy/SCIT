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

    public CosmeticUI() {}

    public void mainMenu(){
        String choice;

        System.out.print("===[ 화장품 관리 ]===\n" +
                         "1. 상품 등록\n" +
                         "2. 상품 수정\n" +
                         "3. 상품 삭제\n" +
                         "4. 상품 조회\n" +
                         "5. 전체 상품 조회\n" +
                         "0. 종 료\n" +
                         "===============%n");
        System.out.print("  > 선택: "); choice = keyin.next();

        switch(choice){
            case "1" : makeProduct(); break;
            case "2" : updateProduct(); break;
            case "3" : deleteProduct(); break;
            case "4" :
        }


    }
    public String subMenu(){return null;}
    public Product inputProduct(){return null;}
    public void makeProduct(){}
    public Lipstick makeLipstick(Product product){return null;}
    public Foundation makeFoundation(Product product){return null;}
    public void searchProduct(){}
    public void updateProduct(){}
    public void deleteProduct(){}
    public void productPrint(){}



}
