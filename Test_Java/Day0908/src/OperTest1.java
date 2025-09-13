public class OperTest1 {
    public static void main(String[] args) {
        // 1) 산술연산자
        int a = 5;
        int b = 2;

        System.out.println("5를 2로 나눈 나머지: " + (a % b));

        // 3의 배수의 정의: 3으로 나눴을 때 나누어 떨어지는 수 (나머지가 없는 수)
        // 0이라고 출력되면 3의 배수임!
        a = 33;
        System.out.println("33은 3의 배수인가? : " + (a % 3));

        // 비교연산자
        a = 10;
        b = 15;
        System.out.println("a가 b보다 크다 : " + (a > b));

        // 비교 연산자 (나이가 8~18세 사이인지 확인)
        // &&(그리고) : 특정 값이 원하는 범위안에 포함되었는지 확인
        int age = 10;

        boolean discount= (age >= 8) && (age < 18);    // 8 <= age < 18;

        // || (이거나) : 일주일간 근무시간이 50이상이고 60미만이 아닌경우
        int workHour = 45;
        boolean r = !(workHour < 50 || workHour >= 60);
                  //  workHour >= 50 && workHour < 60);
    }
}
