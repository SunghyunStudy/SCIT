import java.util.Scanner;

public class Exam_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double length, exchange;
        String unit;

        System.out.print("입력단위는 (1. cm, 2. Inch) : ");
        unit = sc.next();
        System.out.print("길이 입력 : ");
        length = sc.nextDouble();


        if(unit.equals("1")){
            exchange = length * 0.3937;
            System.out.printf("%.0f cm = %f inch%n", length, exchange);
        }
        else if(unit.equals("2")){
            exchange = length * 2.54;
            System.out.printf("%.0f inch = %f cm%n", length, exchange);
        }else System.out.println("단위를 확인하십시오");


    }
}
