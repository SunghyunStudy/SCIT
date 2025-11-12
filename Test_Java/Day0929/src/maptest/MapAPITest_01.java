package maptest;

import java.util.HashMap;
import java.util.Map;

public class MapAPITest_01 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();     // Map

        map.put("전우치", 85);
        map.put("저팔계", 90);
        map.put("홍길동", 80);
        map.put("사오정", 95);

        System.out.println("인원수 : " + map.size());
        System.out.println("등록된 인원이 없니? " + map.isEmpty()); // 비어있으면 true, 값이 있다면 false
        System.out.println("손오공은 등록됐니? " + map.containsKey("손오공")); // key에 해당 값이 포함되어있는지? 있다면 true, 없는 데이터라면 false
        System.out.println("점수가 100인 친구가 있니? " + map.containsValue(100));    // value에 해당 값이 포함되어 있는지?

        Integer delData = map.remove("사오정");  // 삭제하고 싶은 데이터의 key값을 입력 , 해당 데이터의 value가 반환됨
        System.out.println("사오정 삭제 : " + ((delData == null) ? "삭제못함" : "삭제완료"));  // delData == null => 삭제 못함

        Integer result = map.put("삼장법사", 100);  // put() 데이터를 넣을 수도 있고 수정도 할 수 있음. // null이 반환되면 삽입이 되었다는 뜻임
        System.out.println("삽입결과 : " + result);

        result = map.put("삼장법사", 92);           // put()으로 수정을 할 경우 수정 전의 value가 반환됨
        System.out.println("수정결과 : " + result);

        // 키는 중복되면 안됨. -> 키가 같은 경우의 데이터가 존재하면 기존 데이터가 날아가버림.
        // 새로운 데이터로 대체됨
        // 유일한 값이어야 한다. (equals(), hashCode()가 오버라이드가 되어 있는 클래스는 키가 될 수 있다.
        // hashCode()가 같다고 해서 같은 객체가 아니다!! 매우 중요
        // 자바는 주소는 절대 안가르쳐 준다.!



    }
}
