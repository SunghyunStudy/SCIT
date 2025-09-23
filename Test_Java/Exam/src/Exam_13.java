import java.util.Scanner;

public class Exam_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code;
        int usedAmount, fee = 0;
        double totalFee;

        System.out.print("* 사용자 코드를 입력하시오(1 : 가정용 / 2 : 상업용 / 3 : 공업용) : ");
        code = sc.next();
        System.out.print("* 사용량을 입력하시오(ton단위) : ");
        usedAmount = sc.nextInt();

        switch (code){
            case "1" : fee = usedAmount * 50; break;
            case "2" : fee = usedAmount * 45; break;
            case "3" : fee = usedAmount * 30; break;
            default:
                System.out.println("사용자 코드를 잘못 입력했습니다."); break;
        }

        totalFee = fee + fee * 0.05;

        System.out.printf("* 총 수도요금 : %.0f원", totalFee);
    }
}
