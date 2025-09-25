public class StringAPITest_02 {
    public static void main(String[] args) {
        // 객체를 new 하고 메모리 정리 ==> JVM의 부하가 많이 걸리는 작업
        String s1 = "Korea";                        // String Pool
        String s2 = "Korea";

        String s3 = s1;                             // 주소를 복사
        String s4 = new String("Korea"); // Heap

        // System.out.println(s1 == s3);            // 주소비교 t
        // System.out.println(s1.equals((s3)));     // 데이터 비교 t

        // System.out.println(s1 == s4);               // 주소비교 f
        // System.out.println(s1.equals(s4));          // 데이터 비교 t

        System.out.println(s1 == s2);               // 주소비교 t
        System.out.println(s1.equals(s2));          // 데이터 비교 t

        String s5 = "대한민국";
        String s6 = s5 + " Korea" +" 동해물과 " + "백두산이" ;   // 다른 주소에 만든다.

        // String 변경 불가능 ==> immutable
        // StringBuffer StringBuilder // 변경가능한 문자열, mutable
    }
}
