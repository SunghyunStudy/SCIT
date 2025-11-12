import java.security.cert.PolicyNode;

class Student {
    private String name;
    private int age;

    public Student(){

    }

    public Student(int age, String name){
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Junior extends Student{
    private String grade;

    public Junior(){}

    public Junior(String grade){
        this.grade = grade;
    }

    public Junior(int age, String name, String grade){
        super(age, name);
        this.grade = grade;
    }
}

public class ObjectAPITest_01 {
    public static void main(String[] args) {
        Student s = new Student();
        Junior j = new Junior();   // A is a B,  B가 부모

        System.out.println(s instanceof Student); // s 가 student 타입이니?
        System.out.println(j instanceof Junior);
        System.out.println(j instanceof Student);
        System.out.println(s instanceof Junior);  // false
        System.out.println(s instanceof Object);
        System.out.println(s instanceof Object);
//        System.out.println(s instanceof String);  이건 비교 못함

        Student ss = new Junior();
        System.out.println(ss instanceof Student); // true   // 객체 instanceof 타입
        System.out.println(ss instanceof Junior); // true

        /*
        간단히 말해, instanceof는
        "이 변수가 실제로 가리키고 있는 객체가 이 클래스로 만들어졌나요?"
        혹은 "이 클래스를 상속받았나요?"
        를 묻는 것이라고 생각할 수 있습니다.
        ss는 실제로 Junior 객체를 가리키고 있기 때문에
        두 번째 조건문도 true가 됩니다.
         */

//        Polygon p = new Circle();
//        System.out.println(p instanceof Circle); // true
//        System.out.println(p instanceof Rectangle); // false
    }
}
