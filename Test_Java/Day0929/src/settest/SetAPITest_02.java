package settest;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Friend{
    String name;
    String phone;

    public Friend(){}

    public Friend(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Friend friend)) return false;
        return Objects.equals(name, friend.name) && Objects.equals(phone, friend.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}


public class SetAPITest_02 {
    public static void main(String[] args) {
        // Set의 특징 : 데이터를 계속 쌓을 순 있지만그 중 하나의 데이터만 꺼내는 것은 불가능.
        // iterator로 전체 순회는 가능
        Set<Friend> group = new HashSet<>();

        group.add(new Friend("홍길동", "010"));
        group.add(new Friend("여우", "019"));
        group.add(new Friend("토끼", "011"));
        group.add(new Friend("고양이", "013"));
        group.add(new Friend("강아지", "016"));

        // 그룹 인원수 체크
        System.out.println("그룹 인원 수 : " + group.size());

        // 한 개의 데이터를 꺼낼 수 없다는 사실을 알게 되었음.

        // 같은 데이터라고 생각되는 데이터를 넣어보면???
        // 중복된 데이터가 들어가지 않도록 하려면 : hashCode() 와 equals()를 오버라이딩 해야됨.!!!!!!!
        // equals랑 hashcode 오버라이딩 하니까 중복 안됨 신기방기
        group.add(new Friend("토끼", "011"));     // 중복됨.
        System.out.println("그룹 인원 수 : " + group.size());
    }
}
