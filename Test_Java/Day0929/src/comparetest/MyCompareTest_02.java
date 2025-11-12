package comparetest;

 /*
        String str = "abcd";

        // 1) 비교대상에 문자열이 포함되어있을 경우
        System.out.println( str.compareTo("abcd") );  // 0 (같은 경우는 숫자나 문자나 0을 리턴)
        System.out.println( str.compareTo("ab") );  //  2
        System.out.println( str.compareTo("a") );  //  3
        System.out.println( str.compareTo("c") );  //  -2
        System.out.println( "".compareTo(str) );  //  -4

        // 2) 비교대상과 전혀 다른 문자열인 경우
        System.out.println( str.compareTo("zefd") );  //  -25
        System.out.println( str.compareTo("zEFd") );  //  -25
        System.out.println( str.compareTo("ABCD") );  //  32
         */


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Friend implements Comparable<Friend>{ // 객체를 비교가능하게 만들기 위한 implements
    String name;
    String phone;

    public Friend(){}

    public Friend(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    // 이걸 해야지 Comparable을 imple할 수 있음.
    @Override
    public int compareTo(Friend o) {
        int result = (name.compareTo(o.name)) > 0 ? 1 :
                     (name.compareTo(o.name)) < 0 ? -1 : 0; // 자신의 name과 전달받은 name을 비교
        return result;
    }
}

public class MyCompareTest_02 {
    public static void main(String[] args){
        // 우리가 직접 작성한 객체를 비교하고 싶으면??
        // 클래스 내부에 비교가 가능한 comparTo를 만들면 됨
        // 비교할 수 있도록 해주는 인터페이스를 구현하면 됨!
        // 왜 구현해야하나요? 정렬해서 출력하기 위해!
        // String 클래스도 Comarable 인터페이스로 구현했던 것이었음.!! 그래서 compareTo로 비교가 가능했던거..
        Friend f1 = new Friend("손오공", "010-1234-5678");
        Friend f2 = new Friend("전우치", "011-1245-5689");
        Friend f3 = new Friend("전우치", "011-1245-5689");

        int result = f1.compareTo(f2);  // f1이 작음 음수.
        System.out.println(result);

        result = f2.compareTo(f3);  // 같음
        System.out.println(result);


        // 1) 정렬하기위한 전재 조건 : 그 객체에 compareTo 가 오버라이딩이 되어 있어야 한다.

        List<Friend> list = new ArrayList<>();

        list.add(new Friend("이몽룡", "1234"));
        list.add(new Friend("성춘향", "5678"));
        list.add(new Friend("심봉사", "4472"));
        list.add(new Friend("김방자", "4682"));
        list.add(new Friend("한향단", "8653"));


        list.sort((c,d) -> c.compareTo(d) ); // (c,d) -> c.compareTo(d) : 정순정렬   (d,c) -> c.compareTo(d) : 역순정렬

        //정렬이 되었는지 확인.
        // 이름순 정렬임.
        System.out.println("=== 이름 순으로 정렬 ===");
        for(Friend f : list){
            System.out.println(f.name + " : " + f.phone);
        }

        // 전화번호 순으로 정렬해봅시다.
        Collections.sort(list, (c,d) ->
                c.phone.compareTo(d.phone) > 0 ? 1 : c.phone.compareTo(d.phone) < 0 ? -1 : 0);

        // 정렬이 되었는지 확인
        System.out.println("=== 전화번호 순으로 정렬 ===");
        for(Friend f : list){
            System.out.println(f.name + " : " + f.phone);
        }
    }
}
