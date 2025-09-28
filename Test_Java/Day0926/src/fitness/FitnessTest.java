package fitness;

import java.util.ArrayList;
import java.util.List;

public class FitnessTest {
    public static void main(String[] args) {
        // 1명의 데이터를 입력하고 출력
        Fitness f = new Fitness("1", "홍련", false , 165.7, 61);
        System.out.println(f);

        Fitness f2 = new Fitness("2", "오공", true , 172.7, 77);
        System.out.println(f2);

        // 회원등록

        List<Fitness> list = new ArrayList<>();
        list.add(f);
        list.add(f2);
        list.add(new Fitness("3", "김장화", false , 160, 55));
        list.add(new Fitness("4", "저팔계", true , 175.6, 67));


        System.out.println();
        list.forEach(s -> System.out.println(s));


    }
}
