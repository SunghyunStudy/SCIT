import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArrayTest_01 {
    public static void main(String[] args) {
        ArrayList a = new ArrayList(); // 얘는 <Object>가 생략된거임. => 클래스로 된건 전부 처리해준다는 의미
        a.add("사과");
        a.add("123");
        a.add(15);              // --> Integer로 자동변환(auto boxing)되어 저장됨.
        a.add(LocalDate.now());




        System.out.println(a.get(0).getClass());   // .get : 0번 인덱스의 값을 꺼냄 .getClass() : 형태?
        System.out.println(a);
        // 위에꺼로 선언하지 마삼. 아래꺼를 써야함.
        // 제네릭을 지정한 어레이리스트
        ArrayList<String> b = new ArrayList<>();
        b.add("사과");
        b.add("123");
//        b.add(15);                    잘못된 데이터가 들어가지 않도록 자동으로 막음
//        b.add(LocalDate.now());
        System.out.println(b);

        // list는 인터페이스, arraylist는 클래스임.
        String[] ary = new String[10];
        List<String> list = new ArrayList<>();

        // 데이터 삽입
        ary[0] = "추석";
        list.add("한가위");


        // 데이터 꺼내기
        String s1 = ary[0];
        String s2 = list.get(0);
        System.out.println(s2);
        // 데이터 개수 확인
        // 배열은 방의 크기만 확인할 수 있다. 삽입된 데이터의 개수는 개발자가 확인
        System.out.println(ary.length); //방 개수
        System.out.println(list.size());

        // 전체출력
        System.out.println(ary);        // 배열은 주소가 출력됨.
        System.out.println(list);       // toString()이 오버라이드 되어 있어서 데이터가 출력됨
    }
}
