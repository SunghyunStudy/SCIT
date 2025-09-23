import java.util.Scanner;

public class Exam_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int major, general, other;
        String result;

        System.out.print("전공 이수 학점 :");
        major = sc.nextInt();
        System.out.print("교양 이수 학점 :");
        general = sc.nextInt();
        System.out.print("일반 이수 학점 :");
        other = sc.nextInt();

        result = (major >= 70 && (major + general + other) >= 140) ? "졸업가능" : "졸업 불가능";

        System.out.println(result);
    }
}
