package comparetest;

public class MyCompareTest_01 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        String result = (a - b) > 0 ? "a가 b보다 크다" :
                        (a - b) < 0 ? "b가 a보다 크다" : "a와 b가 같다.";

        System.out.println(result);

        // 객체타입은 어떻게 비교?
        String x = "Korea";
        String y = "Corea";

        // 객체는 대놓고 빼기를 못함. 한글자씩 비교하기 어려움.
        // compareTo() 메소드는 String 클래스 안에 포함된 메서드임
        result = x.compareTo(y) > 0 ? "Korea가 Corea보다 크다" :
                x.compareTo(y) < 0 ? "Korea가 Corea보다 작다" : "Korea와 Corea가 같다.";

        System.out.println(result);
    }
}
