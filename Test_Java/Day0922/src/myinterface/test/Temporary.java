package myinterface.test;

public interface Temporary {
    public int VALUE = 15;  // 상수는 반드시 생성과 동시에 초기화 해야됨
                            // static final으로 선언된 멤버 변수(상수)는 모두 대문자로 표기하는 것이 관례
                            // public을 입력하지 않아도 자동으로 입력됨
                            // 메소드 내에 선언된 변수형태의 데이터는 public static final의 상수만 존재한다. → 멤버변수를 가질 수 없다.

//    public Temporary() {}         // 인터페이스는 생성자, setter, getter 금지

    public void create();   // ==> public abstract void create() 라는 의미임. abstract가 생략되어있음

    public void read();
    public void update();
    public void delete();           // 프젝할때 주로 메서드 가이드라인을 만들때 사용하기도 함.
}
