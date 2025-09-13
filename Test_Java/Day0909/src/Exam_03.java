import java.util.Scanner;

/*
    점수 3개(it, japanese, attendance)를 입력받는다.
    과락 점수가 있음 : 40점 미만의 과목이 하나라도 있을 시 Fail
    it 40%, j 30%, a 30%로 환산한 점수의 합이 70점 이상이면 Pass, 아니면 Fail

    <실행>
    it : 75
    일본어: 80
    출결: 70

    최종 결과점수 : (75 * 0.4 + 80 * 0.3 + 70 * 0.3) >= 70이면 Pass, 아니면 Fail
 */
/*
public class Exam_03 {
    public static void main(String[] args){
        Scanner keyin = new Scanner(System.in);
        int it, jp, at;
        double total;
        String result;

        System.out.println("it점수?");
        it = keyin.nextInt();
        System.out.println("일본어 점수?");
        jp = keyin.nextInt();
        System.out.println("출결 점수?");
        at = keyin.nextInt();

        total = (it*0.4+jp*0.3+at*0.3);

        result = (total >= 70 ) ? ((it>=40 && jp>=40 && at>=40) ? "Pass" : "과락이 있으므로 최종 Fail") : "Fail";
        
        System.out.printf("최종 결과점수 : %.1f점 / %s입니다.", total, result);
    }
}
*/

 //  교수님 답안
public class Exam_03 {
    public static void main(String[] args){
        Scanner keyin = new Scanner(System.in);
        int it, jp, at;
        double total;
        boolean isFailure;

        System.out.println("it점수?");
        it = keyin.nextInt();
        System.out.println("일본어 점수?");
        jp = keyin.nextInt();
        System.out.println("출결 점수?");
        at = keyin.nextInt();

        //과락 여부 확인.
        isFailure = (it<40 || jp<40 || at<40) ? true : false;

        total = (it*0.4+jp*0.3+at*0.3);



        System.out.printf("최종 결과점수 : %.1f점이고, 과락이 %s음! 최종 %s입니다.%n", total, (isFailure) ? "있" : "없",
        (total >= 70 && !isFailure) ? "Pass" : "Fail" );
    }
}
