public class WhileTest_02 {
    public static void main(String[] args) {
        int i = 0;

        while (i < 10){
            if (i % 2 == 0) break;
            System.out.println(++i);
        }
    }
}
