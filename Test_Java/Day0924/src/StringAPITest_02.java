import java.io.SequenceInputStream;

public class StringAPITest_02 {
    public static void main(String[] args) {

        // 객체를 new 하고 메모리에서 지우는 거 -> JVM에 부하가 많이 걸리는 작업.
        String s1 = "Korea";                // String Pool(상수영역)영역에 저장됨 / 같은 데이터가 딱 하나만 들어갈 수 있음
        String s2 = "Korea";                // 상수영역에 저장되기 때문에ㅔ Korea는 단 하나만 저장됨.
        String s3 = s1;
        String s4= new String("Korea");

        System.out.println(s1 == s4);   //주소비교 //100번지, 300번지 다를 수 있음
        System.out.println(s1.equals(s4)); // 값비교


        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String s5 = "대한민국"; // String은 값을 바꿀 수 없음 ********* immutable ***********
        String s6 = s5 + " Korea" + "동해물과 " + "백두산이";      // 데이터가 바뀌는게 아님 s5가 100번지라면 다른 주소에 만들어짐. .
        // s6는 객체가 지워졌다가 만들어지는걸 3번 반복함.


//        StringBuffer StringBuilder // 얜 변경가능한 문자열임 ******* mutable *******
    }
}
