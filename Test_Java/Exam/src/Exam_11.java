import java.util.Scanner;

public class Exam_11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height, weight;
        double stdWeight;

        System.out.print("키 입력 : ");
        height = sc.nextInt();

        System.out.print("몸무게 입력 : ");
        weight = sc.nextInt();

        stdWeight = (height - 100) * 0.9;

        if(weight >= stdWeight - 10 && weight <= stdWeight + 10){
            System.out.println("정상 체중입니다.");
        }
        else if (weight < stdWeight - 10) System.out.println("저체중입니다.");
        else if (weight > stdWeight + 10) System.out.println("과체중입니다.");


    }
}
