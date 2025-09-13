public class OperTest_01 {
    public static void main(String[] args) {
        int a = 10;   // 메모리에 0000 0000 0000 1010     1bit에 숫자 하나임.
        int b = ~a;   // 메모리에 0000 0000 0000 0101

        System.out.println(a);
        System.out.printf("%d의 비트모양 ==> %s%n", a, Integer.toBinaryString(a));
        System.out.printf("%d의 비트모양 ==> %s%n", b, Integer.toBinaryString(b));

        int c = 1;      // 0000 0000 0000 0001 = 1
        System.out.printf("%d ==> %s%n", c, Integer.toBinaryString(c));

        c = c << 1;     // 0000 0000 0000 0010 = 2
        System.out.printf("%d ==> %s%n", c, Integer.toBinaryString(c));

        c = c << 1;     // 0000 0000 0000 0100 = 4
        // 1000 0000 0000 0000 -> 0이 아님 0에 가장 가까운 음수임.
        System.out.printf("%d ==> %s%n", c, Integer.toBinaryString(c));


        // ---------------------------------------
        int d, e, f;
        d = 0b1101;  // b = 13    // 0000 1101
        e = 0b1010;  // e = 10    // 0000 1010

        f = d & e;  // and           // 0000 1000
        System.out.printf("%d ==> %s%n", f, Integer.toBinaryString(f));

        f = d | e;  // or           // 0000 1000
        System.out.printf("%d ==> %s%n", f, Integer.toBinaryString(f));

        f = d ^ e;  // xor          // 0000 0111
        System.out.printf("%d ==> %s%n", f, Integer.toBinaryString(f));

        int finalData = 2147483647; // int -> - 2^31 ~ 2^31 - 1    -2147483648 ~ 2147483647
        finalData++;   // 0111 1111 1111 1111 1111 1111 1111 1111 -> 1000 0000 0000 0000 0000 0000 0000 0000
        System.out.printf("%d ==> %s%n", finalData, Integer.toBinaryString(finalData));
    }
}
