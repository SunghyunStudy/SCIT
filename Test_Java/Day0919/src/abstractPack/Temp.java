package abstractPack;

// Concrete class
// 클래스명 == 파일명 // static은 파일명과 똑같은 클래스만 붙을 수 있음
class MyClass{
    String name = "이름없음";
}

// abstract class(객체 생성할 수 없음)
// 상속 전용임.
// 추상메소드를 가지면 추상 클래스가 된다.
abstract class MyAbstract{
    int age = 10;
    public MyAbstract() {}
    public void output(){
        System.out.println("나이는 " + age);
    }
    // 추상메소드란? 앞에 abstract 키워드가 있고! and 메소드 시그니처만 있는 메소드
    public abstract void add(int a, int b);
}

// 추상클래스를 상속받은 자식 클래스가 concrete class가 되려면,
// 반드시!!!!!!!! 부모의 추상메소드를 구현(override) 해야됨
class ChildAbstract extends MyAbstract{

    @Override
    public void add(int a, int b){ // 이걸 해야 구현이 되는듯 이거 없으니까 오류 났었음. 지워보셈.
        System.out.println("구현한 메소드의 결과 " + (a + b));
    }

}

public class Temp {
    public static void main(String[] args) {
        MyClass a = new MyClass();     // 객체 생성 가능한 클래스 ( = Concrete class)
        System.out.println(a.name);

//        MyAbstract b = new MyAbstract();  // 객체 생성을 못하는 클래스 (= abstract class)
        ChildAbstract c = new ChildAbstract();  // 객체 생성 가능 (상속전용클래스)

    }
}

