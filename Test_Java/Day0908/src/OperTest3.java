public class OperTest3 {
    public static void main(String[] args) {
        int a = 0, b;

        ++a;
        System.out.printf("1) a = %d%n", a);

        a++;
        System.out.printf("2) a = %d%n", a);

        // ============
        a = 0;
        b = ++a;   // 증가 -> 대입
        System.out.printf("3) a = %d, b = %d%n", a, b);

        // ============
        a = 0;
        b = a++;   // 대입 -> 증가
        System.out.printf("4) a = %d, b = %d%n", a, b);
    }
}
