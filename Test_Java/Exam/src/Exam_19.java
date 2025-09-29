import java.util.Scanner;

public class Exam_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input, count = 0;

        System.out.print("정수를 입력 하세요. ");
        input = sc.nextInt();

        while(input > count){
            count++;
            if(input % count == 0){
                System.out.println(count);
            }
        }


    }
}
