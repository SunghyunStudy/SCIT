public class StringAPITest_01 {
    public static void main(String[] args) {
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html#constructor-summary

        // 1. 생성자 이용하는 방법
        String s1 = new String();
        System.out.println(s1);

        // 2. 바이트배열
        byte[] barr = {97, 100, 102, 126}; // byte라서 127까지만 넣을 수 있음
        String s2 = new String(barr);
        System.out.println(s2);

        // offset : 현재를 기준으로 얼마나 떨어져 있는지.
        String s3 = new String(barr, 1, 2);
        System.out.println(s3);

        char[] carr = {'K', 'o', 'r', 'e', 'a'};
        String s4 = new String(carr);
        System.out.println(s4);


        String s5 = new String("Japan");
        System.out.println(s5);

        String s6 = "Japan";
        System.out.println(s6);
    }
}
