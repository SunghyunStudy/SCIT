import java.util.Scanner;

public class InputTest_01 {
    public static void main(String[] args) {
        Scanner keyin = new Scanner(System.in); // System이라는 클래스 안의 in이라는 멤버변수.

        String name;
        double height, weight;
        boolean gender;         // 성별. true이면 남성, false이면 여성이라고 가정

        double stdWeight;
        double bmi;

        System.out.print("이름 : ");
        name = keyin.next();
        System.out.print("성별 : ");
        gender = keyin.nextBoolean();
        System.out.print("키(cm) : ");
        height = keyin.nextDouble();
        System.out.print("몸무게(kg) : ");
        weight = keyin.nextDouble();

        // 적정체중을 구하여 출력
        stdWeight = (height - 100) * 0.9;

        // bmi 구하기
        bmi = weight / ((height*0.01) * (height*0.01)) ;

        System.out.printf("이름: %s, 성별: %s, 키: %.0fcm, 몸무게: %.0fkg", name, (gender) ? "남성" : "여성" , height, weight);
        System.out.printf(", 적정체중: %.2fcm, BMI: %.2f", stdWeight, bmi);


    }
}
