/* switch ~ case문 연습

전달받은 값을 3으로 나눈 나머지 : 0, 1, 2


 */

public class SwitchTest_01 {
    public static void main(String[] args) {
        int a = 10;

        switch (a % 3){
            case 0:
                System.out.println("나머지가 0"); break;
            case 1:
                System.out.println("나머지가 1"); break;
            case 2:
                System.out.println("나머지가 2"); break;
        }


    }
}
