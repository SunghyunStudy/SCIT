package scoreTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScoreTest {
    public static void main(String[] args) {

        List<Score> list = Arrays.asList(
                new Score("21", "홍길동", 89, 45),
                new Score("19", "이몽룡", 70, 45),
                new Score("30", "성춘향", 89, 45),
                new Score("44", "변사또", 89, 45),
                new Score("13", "손오공", 50, 45)
        );
        // Arrays.asList()를 사용하지 않을 경우
//        List<Score> list = new ArrayList<>();
//        list.add(new Score("21", "홍길동", 89, 45));
//        list.add(new Score("19", "이몽룡", 70, 45));
//        list.add(new Score("30", "성춘향", 89, 45));
//        list.add(new Score("44", "변사또", 89, 45));
//        list.add(new Score("13", "손오공", 50, 45));


        // 학번 순으로 전체 출력
        print(list);

    }

    private static void print(List<Score> list){
//        Collections.sort(list, (c, d) ->
//                c.getId().compareTo(d.getId()) > 0 ? 1 : c.getId().compareTo(d.getId()) < 0 ? -1 : 0);


//         지피티
//        Collections.sort(list);
//

        // 이게 가장 좋은 방법임
        // x.compareTo(y) 가 양수면 x, y 순서를 바꿈
        // 음수면 x, y 순서를 그대로 둠.
        list.sort((Score x, Score y) -> x.compareTo(y));
        // 아래는 내림차순
        list.sort((c, d) -> d.compareTo(c));

        // 교수님 코드
        for(int i = 0; i < list.size(); ++i){
            try{
                Score score = list.get(i);
                System.out.print(list.get(i));

                if(score.getAvg() < 60) throw new ScoreException("재시험 대상자");
                System.out.println();
            }
            catch (ScoreException e){
                System.out.println(" " + e.getMessage());
            }
        }
    }
}
