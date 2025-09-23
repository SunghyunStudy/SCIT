package myinterface.poly;

interface Pet{
    public static final int LEG_COUNT = 4;
    public void cry();  // abstract 메소드
}

class Cat implements Pet{

    @Override
    public void cry() {
        System.out.println("냐옹~");
    }
}

class Dog implements Pet{

    @Override
    public void cry() {
        System.out.println("멍멍");
    }
}

class Pig implements Pet{

    @Override
    public void cry() {
        System.out.println("꿀꿀");
    }
}


public class InterfaceTest {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Pig pig = new Pig();


        cry(cat);  // 아래의 메소드임. 아래 메소드로 가서 pet.cry()를 호출 근데 pet의 cry()는 추상 메소드라 cat.cry로 동적 바인딩 됨.
        cry(dog);
        cry(pig);
    }
    // 다형성을 이용해서 처리한 메소드
    // pet 메소드는 Pet타입임.
    // 그러나 실행하는 runtime 에서는 Pet이 가진 cry() 메소드가 추상이므로 실행 불가
    // 그래서 자식의 메소드를 대신 호출(동적바인딩) ==> 다형성. 多形性
    public static void cry(Pet pet){    // 매개변수에 부모의 객체를 넣으면 cry(cat)~ 오류가 사라짐
        pet.cry();
    }
}
