package second;

public class SecondTest {
    public static void main(String[] args) {
        Three th = new Three();     // three에서 기본 생성자를 만들지 않았기 때문에 Three()를 호출 못할거 같지만 자동으로 생성됨
        th.setThreeData(25);
        th.output();                // 출력하면 one과 two도 출력이 되는데 super.output() 때문임.

        Two two = new Two();
        two.setTwoData(12);
        two.output();

        One one = new One();
        one.setOneData(34);
        one.output();


    }
}
