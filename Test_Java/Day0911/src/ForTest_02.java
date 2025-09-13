/*
    break : 주로 반복문, switch문을 빠져나가는 명령문. 반복문 내에서 사용될 때에는 if와 함께 사용함.
    continue : 반복문 안에서 continue 문장 하위의 문장을 skip한다는 뜻
 */

public class ForTest_02 {
    public static void main(String[] args) {
        int i = 0;
        while (true){
            i++;
            if(i == 5) continue;
            System.out.println(i);


            if(i > 10) break;
        }

        System.out.println("End");
    }
}
