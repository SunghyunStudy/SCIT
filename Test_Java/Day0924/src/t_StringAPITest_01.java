public class StringAPITest_01 {
    public static void main(String[] args) {
        // 생성자 이용하는 방법
        String s1 = new String(); // "" 과 동일
        System.out.println(s1);

        byte[] barr = {97, 100, 102, 125};
        String s2 = new String(barr);
        System.out.println(s2);

        String s3 = new String(barr, 1,2);
        System.out.println(s3);

        char[] carr = {'K', 'o', 'r', 'e', 'a' };
        String s4 = new String(carr);
        System.out.println(s4);

        String s5 = new String("Japan");
        String s6 = "Japan";
        System.out.println(s5);
        System.out.println(s6);
    }
}
