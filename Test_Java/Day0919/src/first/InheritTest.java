package first;

public class InheritTest {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.setMoney(50);
        p.output();

        // 자식의 클래스를 생성하여 사용
        Child c = new Child();
        c.setProperty(1500);
        c.output();

        // 부모한테 물려받은 메소드를 사용
        c.setMoney(100);
        c.output();

    }
}
