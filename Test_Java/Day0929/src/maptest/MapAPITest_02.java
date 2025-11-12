package maptest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class Friend{
    String name;
    String phone;

    public Friend(){}

    public Friend(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
}


public class MapAPITest_02 {
    public static void main(String[] args) {
        Map<Integer, Friend> map = new HashMap<>();

        // 삽입
        map.put(1, new Friend("이몽룡", "1234"));
        map.put(2, new Friend("성춘향", "0011"));
        map.put(3, new Friend("김방자", "4321"));
        map.put(4, new Friend("황향단", "2222"));
        map.put(5, new Friend("심봉사", "0000"));

        // 수정
        Friend f = new Friend("성춘향", "0111");
        map.put(2,f);

        // 삭제
        f = map.remove(4);
        System.out.println("향단이가 지워졌니? " + ((f != null) ? "향단이는 지워짐." : "향단이는 지워지지 않음"));

        // 조회
        f = map.get(5);
        System.out.println("심봉사의 정보 : " + (f.name) + ", " + (f.phone));

        // 전체 출력
        System.out.println(map);    // toString()으로 출려되는데 toString이 없으니까 출력값이 이상함
        System.out.println("친구는 " + map.size() + "명");

        // 모든 목록을 순회하기 --> 먼저 Key(set)만 출력.
//        map.entrySet();
        Set<Integer> key = map.keySet();  // 전체 데이터에서 key값만 가져옴
        System.out.println(key); // 출력: [1, 2, 3, 5]
        // set은 순서가 없음(인덱스 없음) / 중복 저장이 안됨.
        Iterator<Integer> iter = key.iterator();
        while (iter.hasNext()){
            Integer k = iter.next();        // 키를 꺼냄
            Friend friend = map.get(k);
            System.out.println(friend.name + "  :  " + friend.phone);
        }
    }
}
