import java.util.Scanner;

public class Exam_06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inputNum=0;
        boolean four, five;

        while(true){
            System.out.print("\n정수입력 : ");
            inputNum = sc.nextInt();
            four = inputNum % 4 == 0;
            five = inputNum % 5 == 0;

            if(four && five){
                System.out.println("4와 5로 나누어 집니다.");
            }else {
                System.out.println("4와 5로 나누어 지지 않습니다.");
            }

            if(four || five){
                System.out.println("4또는 5로 나누어집니다.");
            }else{
                System.out.println("4또는 5로 나누어 지지 않습니다.");
            }

            if(!four && five) {
                System.out.println("5로 나누어 집니다.");
            }else if(four && !five){
                System.out.println("4로 나누어 집니다.");
            }
        }
    }
}
