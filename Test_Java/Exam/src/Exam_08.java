import java.util.Scanner;

public class Exam_08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputNum;

        System.out.print("정수 입력 : ");
        inputNum = sc.nextInt();

        if ( (inputNum >= 65 && inputNum <= 90) || (inputNum >= 97 && inputNum <= 122)){
            System.out.print((char)inputNum);
        }else{
            System.out.println("알파벳에 해당하는 코드 값을 입력하시오");
        }


    }
}
