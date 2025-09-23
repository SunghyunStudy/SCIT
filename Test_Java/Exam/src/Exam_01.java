import java.util.Scanner;

public class Exam_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double buyPrice, tax, realPrice;

        System.out.print("제품가격을 입력하세요 : ");
        buyPrice = sc.nextDouble();
        tax = buyPrice * 0.1;
        realPrice = buyPrice - tax;

        System.out.printf("제품의 부가세 : %.1f원%n", tax);
        System.out.printf("제품 원가 : %.1f원", realPrice);

    }
}
