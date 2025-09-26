public class ObjectAPITest_01 {
    public static void main(String[] args) {
        Person p = new Person("홍길동", 26);
//        synchronized () // 객체를 동기화 시키는 것 : 줄 세우기 (동시에 달라붙는걸 방지

        System.out.println("korea".getClass());  // 클래스 정보 출력.  // Reflection
        System.out.println("p1 주소" + p.toString());

        Person p2 = new Person("홍길동", 26);
        System.out.println("p2 주소" + p2.toString());          // p랑 주소가 다름.

        System.out.println(p == p2);   // 주소 비교하는거임
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
    //
    public boolean equals(Object obj){
        if(obj == null) return false; // p.equals(null)을 방지
        if(!(obj instanceof Person)) return false;  // 객체가 아닌 문자열 같은 형태를 방지

        Person temp = (Person)obj; // 하향 캐스팅. obj(얘는 조상임)를 Person객체로 만듬
        // temp안에는 name도 age도 있는 상태임

//        this.age == obj.age; // 이거 안됨 Person객체가 아니기 때문에
        // 이제 temp를 만들었기에
        if(this.age == temp.age && this.name.equals(temp.name)){
            return true;
        }
        return false; // if문 안의 조건이 아니라면 false(= 같지 않다면.)
    }
}