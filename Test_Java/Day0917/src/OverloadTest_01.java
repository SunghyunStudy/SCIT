// 메소드 오버로드 : 전달인자의 개수가 다르거나 타입이 달라야 됨
// 반환타입은 상관없음.

public class OverloadTest_01 {

    public static void main(String[] args) {
        add(10);
        add(10, 20);
        add(25.6);

        System.out.println();
    }

    public static int add(int a){
        return 0;
    }

    public static int add(int a, int b){
        return a + b;
    }

    public static int add (double a){
        return 0;
    }
}
