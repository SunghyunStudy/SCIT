public class VarMethodTest_01 {
    public static void main(String[] args) {
        countCheck();   // 같은 클래스 안에 countCheck() 메소드가 static으로 존재한다는 의미. 앞에 자료형이 없기 때문
        countCheck("korea");
        countCheck("korea", "japan");
        countCheck("apple", "banana", "pear", "melon");

        // 가변인자 메소드 printf()
        System.out.printf("안녕?%n");
        System.out.printf("안녕? %d %s%n", 10, "korea");
        System.out.printf("너의 이름은? %s, %d살, %d학년, %d 반", "임꺽정", 25, 2, 5);
    }

    // 가변인자 메소드 ( 가변인자 메소드 예시 -> System.out.printf("안녕? %d %s", 10, "korea"); )
    private static void countCheck(String... data) {
        System.out.println("파라미터 개수 : " + data.length);
    }

    public static void printf(String data, Object... a){

    }
}
