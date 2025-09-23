import java.util.Scanner;

public class Exam_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double radius, dimension;

        System.out.print("원의 반지름: ");
        radius = sc.nextDouble();
        dimension = radius * radius * 3.14;

        System.out.printf("반지름 %.0f인 원의 면적 : %.2f", radius, dimension);

    }
}
