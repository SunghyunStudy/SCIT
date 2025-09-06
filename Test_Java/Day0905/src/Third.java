public class Third {
    public static void main(String[] args){
        // 리터럴들을 출력해보기 (byte, short는 없음)

        // 정수형 리터럴
        System.out.println(25); // int 리터럴 출력
        System.out.println(30L); // long 리터럴 출력
        System.out.println('※');   // 이것도 정수형 리터럴
        System.out.println('😊');  // 이것도 정수형 리터럴
        System.out.println('私');  // 이것도 정수형 리터럴

        // 실수형 리터럴
        System.out.println(45.195F); // flaot
        System.out.println(3.14);    // double
        System.out.println(1.5e-3);  // double

        // 진위형
        System.out.println(true);
        System.out.println(45 >= 20);

        // 문자열
        System.out.println("❤️");

        System.out.println();
        System.out.print("문자열\n");    // println = print + 줄바꿈 리터럴(\n)
        System.out.print("문자\t타입");
        System.out.print("\n숫자");
        System.out.println("오늘은 자바의 \"자료형\" 에 대해서 배웠다.");

        /*
            <출력 화면>
            문자열
            문자  타입
            숫자
        */
    }
}
