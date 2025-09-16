import java.sql.SQLOutput;
import java.util.Scanner;

public class Exam_29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] seat = new String[] {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        int[] seatCount = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String name;
        int seatIndex, count = 0;


        while(true){
            System.out.println("\n=== 좌석 예약 상태");
            for(int i = 0; i < 10; i++){
                System.out.printf("[%s]", seat[i]);
            }
            System.out.println();
            System.out.println();

            System.out.print("예약자 이름 입력: ");
            name = sc.next();

            System.out.print("좌석 입력(1~10)");
            seatIndex = sc.nextInt();
            if(seatIndex < 1 || seatIndex > 10){
                System.out.println("## 예약을 다시 진행해 주세요");
                continue;
            }
            if(seatCount[seatIndex-1] == 1){
                System.out.println("(warning) 이미 예약된 좌석입니다.");
                System.out.println();
                continue;
            }
            seatCount[seatIndex-1] = 1;
            seat[seatIndex-1] = name;
            count++;

            if(count == 10) {
                System.out.println("# 모든 좌석이 예약 완료되어 종료합니다.");
                for(int i = 0; i < 10; i++){
                    System.out.printf("[%s]", seat[i]);
                }
                break;
            }
        }



    }
}


/*
        String[] allSeat = new String[10];
        while(true){
            System.out.println("=== 좌석 예약 상태");
            for(int i - 0; i< allSeat.length; ++i){
                if(allSeat[i] == null){
                    System.out.println("[    ]");

                }else{
                    System.out.print("[ " + allSeat[i] + " ");
                }
            }
            System.out.println("\n");
            System.out.print("예약자 이름 입력:");
            name = sc.next()

            System.outprint("좌석 입력 (1~10)");

        }
 */