import java.util.Scanner;

public class Exam_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a, b, total;
        String menu, result;

        System.out.print("첫 번째 수 입력 : ");
        a = sc.nextInt();
        System.out.print("두 번째 수 입력 : ");
        b = sc.nextInt();

        System.out.print("=================\n" +
                         "1. 덧셈\n" +
                         "2. 뺄셈\n" +
                         "3. 곱셈\n" +
                         "4. 나눗셈\n" +
                         "=================\n");

        System.out.print("메뉴 선택 : "); menu = sc.next();

        switch(menu){
            case "1" : total = a + b; result = "덧셈 결과"; break;
            case "2" : total = a - b; result = "뺄셈 결과"; break;
            case "3" : total = a * b; result = "곱셈 결과"; break;
            case "4" : total = a / b; result = "나눗셈 결과"; break;
            default :
                System.out.println("없는 메뉴입니다."); return;
        }

        System.out.printf("%s : %.0f", result, total);



    }
}
