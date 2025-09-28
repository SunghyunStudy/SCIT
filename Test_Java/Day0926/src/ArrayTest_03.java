import java.util.ArrayList;
import java.util.List;

public class ArrayTest_03 {
    public static void main(String[] args) {
        List<String> market = new ArrayList<>();

        // 데이터 추가하기
        market.add("바나나");
        market.add("소고기");
        market.add("감자");
        market.add("마늘");
        market.add("사과");

        // 삽입하기 add(index , data);
        market.add(1, "주스"); // 인덱스를 지정해서 삽입

        // 데이터 확인  contains(data)
        boolean result = market.contains("소고기");  // 데이터 중 소고기가 있다면 true 반환
        System.out.println("소고기를 장바구니에 추가했나? " + result);
        market.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // 데이터 교체 set(index, data)
        String data = market.set(2, "돼지고기앞다리살"); // 기존의 들어있던 "소고기"는 변수에 저장됨
        System.out.println("장바구니에서 뺀 물건: "+ data);

        market.forEach(s -> System.out.print(s + " "));
        System.out.println();


        // 삭제하기
        result = market.remove("사과"); // 데이터를 삭제한 뒤 true를 반환
        System.out.println("사과 뺐니? " + result);
        result = market.remove("샤인머스캣"); // 데이터를 삭제한 뒤 true를 반환
        System.out.println("샤인머스캣 뺐니? " + result);


        System.out.println("장바구니에 넣은 물건개수 : " + market.size());
        System.out.println("장바구니가 비었니? " + market.isEmpty());

        market.clear();
        System.out.println("장바구니가 비었니? " + market.isEmpty() + market.size());


    }
}
