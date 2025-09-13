/*
    (문제 2) 1년은 365.2422일이다.

    결과: 365.2422일은 365일 5시간 48분 46.08초입니다.

    (힌트) 하루 : 24시간, 1시간: 60분, 1분: 60초
    1.5시간 : 1시간 30분
 */
public class Exam_02 {
    public static void main(String[] args) {
        double oneYear = 365.2422;
        int day, hour, minute;
        double second, temp;

        day = (int) oneYear;         // 365일
        temp = oneYear - day;        // 남은 값 0.2422일 은 시간~

        hour = (int)(temp * 24);     // 0.2422 * 24 = 시간.분
        temp = (temp * 24) - hour;   // 0.8128 시간

        minute = (int)(temp * 60);   // 48 간추려서 변수에 치환
        temp = (temp * 60) - minute; // 0.768 만 추림

        second = temp * 60;          // 46.08초

//        System.out.println(oneYear+"일은 " + day + "일 " + hour
//                + "시간 " + minute + "분 " + second+"초입니다.");
        System.out.printf("%.4f일은 %d일 %d시간 %d분 %.2f초입니다.%n",
                          oneYear, day, hour, minute, second);
    }
}
