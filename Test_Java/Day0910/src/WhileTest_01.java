public class WhileTest_01 {
    public static void main(String[] args) {
        int i = 0, total = 0;
        while(i < 10){
            ++i;
            total += i;
        }
        System.out.println("1부터 10까지의 합계 : " + total);
    }
}