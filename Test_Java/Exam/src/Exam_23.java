import java.util.List;
import java.util.Scanner;

public class Exam_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] list = new int[10];
        for(int i = 0; i < list.length; i++){
            System.out.printf("%d 번째 데이터 => ", i+1); list[i] = sc.nextInt();
        }

        System.out.println("**** 입력된 데이터 목록 ****");
        for(int i = 0; i < list.length; i++){
            System.out.println(list[i]);
        }
    }
}
