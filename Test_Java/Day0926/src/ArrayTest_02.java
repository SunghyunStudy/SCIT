import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayTest_02 {
    public static void main(String[] args) {
        List<String> market = new ArrayList<>();

        // 데이터 추가하기
        market.add("바나나");
        market.add("소고기");
        market.add("감자");
        market.add("마늘");
        market.add("사과");

        System.out.println("데이터의 개수 : " + market.size());  // 데이터 개수 확인
        String temp = market.get(2);
        System.out.println("꺼낸 데이터 3번 : " + temp);

        // 전체 출력해보기 (for 문을 활용)
        for(int i = 0; i < market.size(); i++){
            System.out.print(market.get(i) + "  ");
        }
        System.out.println();

        // 전체 출력해보기 : advanced for 반복문
        for(String a : market) System.out.print(a + "  ");
        System.out.println();

        // 전체 출력해보기 :  Iterator 로 꺼내기 --> array를 이터레이터로 반환받는다. while로 순회
        Iterator<String> iter = market.iterator();
        while(iter.hasNext()) System.out.print(iter.next() + "  ");  // iter에 다음 데이터가 있다면 일단 꺼내고 다음으로 바꿈 오른쪽++ 같은 느낌
        System.out.println();

        // 전체 출력해보기 :  Lambda 메소드로 꺼내기 => forEach() 메소드 내에 람다식을 넣어서 출력
        market.forEach(s -> System.out.print(s + "  "));
        //forEach는 컨수머임 컨수머 형태의 데이터는 s(타입 입력 안해도 됨) -> xxx 형태로 반환받음



    }

    // 요런 형태가 Consumer
    public void print(String s){
        System.out.println(s);          // 얘처럼 반환이 없는게 컨수머임.
    }

    // Producer는 받는거 없이 반환만 하는 메소드
    public int myValue(){
        return (int)(Math.random() * 100);
    }
}
