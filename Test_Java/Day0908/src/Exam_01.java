/*
    (문제 1) 출력문과 산술연산자만을 이용하여 적정체중 구하기
    적정체중 = (키(cm) - 100) * 0.9

    키 165.5cm의 적정체중은 xx.xxxxxkg입니다.

    // 변수: 키(height, double), 적정체중: (stdWeight, double)
 */
public class Exam_01 {
    public static void main(String[] args) {
        double height = 162.5;
        double stdWeight;

        stdWeight = (height - 100) * 0.9;

        System.out.println("키 " + height + "cm의 적정체중은 " + stdWeight + "kg입니다.");
    }
}
