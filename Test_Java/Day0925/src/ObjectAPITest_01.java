import java.util.Objects;

public class ObjectAPITest_01 {
    public static void main(String[] args) {
        Person p = new Person("홍길동", 26);
//        synchronized () // 객체를 동기화 시키는 것 : 줄 세우기 (동시에 달라붙는걸 방지

        System.out.println("korea".getClass());  // 클래스 정보 출력.  // Reflection
        System.out.println("p1 주소" + p.toString());
        System.out.println("길동이-1의 주소 :" + p.hashCode());

        p.name = "꺽정";
        System.out.println("바뀐이름-1의 이름 " + p.name);


        Person p2 = new Person("홍길동", 26);
        System.out.println("p2 주소" + p2.toString());          // p랑 주소가 다름.
        // hashcode 메소드를 쓰면 값이 같다면 주소를 맞춰줌
        System.out.println("길동이-2의 주소 :" + p2.hashCode());
        System.out.println("바뀐이름-1의 이름 " + p2.name);

        // 지금 hashcode를 썼는데 바로 아래 p == p2는 주소가 false로 뜸 주소가 똑같다면 지금 p2, p의 이름이 똑같아야됨
        //
        System.out.println(p == p2);   // 주소 비교하는거임 //
        System.out.println(p.equals(p2));  //같은 객체인지 비교하는 거지만 // equals를 직접 오버로딩을 하지 않으면 주소비교랑 똑같음. // 고쳐써야됨

//        p.equals(null);
//        p.equals("길동이"); // 문자열도 object의 자식이기 때문에 오류가 안남. 대신 (Person)obj; 하향캐스팅이 불가

    }
}

class Person /* extends Object */{
    String name;
    int age;



    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    // generate로 만든 equals
    // equals : 멤버 데이터가 같은지 확인하는 메소드
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (!(o instanceof Person person)) return false;
//        return age == person.age && Objects.equals(name, person.name);
//    }



// 이게 뭔지?
    // hash 알고리즘 : 데이터를 저장할때 일일히 찾아가면 느린데 값을 알면 주소를 바로 찾을 수 있음.
    @Override
    public int hashCode() { // 데이터가 같은 경우 객체가 달라도 주소를 맞춘다.!
        return Objects.hash(name, age);
    }


    public boolean equals(Object obj){
        if(obj == null) return false; // p.equals(null)을 방지
        if(!(obj instanceof Person)) return false;  // 객체가 아닌 문자열 같은 형태를 방지

        Person temp = (Person)obj; // 하향 캐스팅. obj(얘는 조상임)를 Person객체로 만듬
        // temp안에는 name도 age도 있는 상태임

//        this.age == obj.age; // 이거 안됨 Person객체가 아니기 때문에
        // 이제 temp를 만들었기에
        // 이거 generate에서 만들 수도 있음
        if(this.age == temp.age && this.name.equals(temp.name)){
            return true;
        }
        return false; // if문 안의 조건이 아니라면 false(= 같지 않다면.)
    }
}