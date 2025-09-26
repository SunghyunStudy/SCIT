/*
    정수 배열 10개를 생성하고, 50 ~ 100 미만의 정수를 발생시켜 배열에 저장하시오!
 */
// java.lang 에 들어있는 클래스 -> System, String, StringBuilder, StringBuffer, Math, Object, Wrapper

public class Exam_Math01 {
    public static void main(String[] args) {
        int[] iary = new int[10];
        double rand;
        for (int i = 0; i < 10; ++i){
            rand = Math.random() * 50 + 50;
            iary[i] = (int)rand;
        }

        for(int j = 0; j < 10; ++j){
            System.out.print(iary[j] + " ");
        }
        System.out.println();

        // 출력 : 배열을 위한 반복문
        // 콜론(:)을 기준으로 오른쪽은 배열을, 왼쪽은 변수를 입력 ( advanced for 문 )
        for (int a : iary){
            System.out.print(a + " ");
        }
        System.out.println();

        // 방금 만든 난수 중에서 최대 최소값 출력
        int a = 0, b = 0;
        for (int i = 0; i < iary.length - 1; ++i){
            a = Math.max(iary[i], iary[i+1]);
            b = Math.min(iary[i], iary[i+1]);
        }

        System.out.print("최대값 : " + a + " ");
        System.out.print("최소값 : " + b);

        

    }
}
