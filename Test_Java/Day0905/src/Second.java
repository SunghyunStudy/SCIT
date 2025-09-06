/*
    강형 언어 (Java, C++, C# 등)
    약형 언어 (Python, JavaScript 등)
    자료형 (Data Type)
 */

public class Second {
    public static void main(String[] args) {
        // 변수 선언 예시: byte 타입 (1바이트, -128 ~ 127 범위)
        byte byteVar;    // 1) 변수 선언
        byteVar = 15;    // 2) 값 초기화
        // 3) 사용: System.out.println(byteVar); // 출력: 15

        // short 타입 (2바이트, -32768 ~ 32767 범위) - 선언과 동시에 초기화
        short shortVar = 100;

        // int 타입 (4바이트, 약 -21억 ~ 21억 범위) - 기본 정수형
        int intVar1;   // 선언
        intVar1 = 25;  // 초기화
        System.out.println(intVar1);  // 출력: 25

        // long 타입 (8바이트, 매우 큰 범위) - 큰 정수에 사용, L 접미사 필요 시
        long longVar = 123;
        System.out.println(longVar);  // 출력: 123

        // int 타입 - 가독성을 높이기 위해 _(언더바)를 숫자 중간에 넣을 수 있음 (컴파일 시 무시됨)
        int intVar2 = 1_234_567_890;
        System.out.println(intVar2);  // 출력: 1234567890

        /* < 대입연산자 : assignment >
            Right Value의 값, 식, 메소드, 호출의 결과값을 Left Value인 변수에 대입
            Left Value는 변수만 올 수 있다.
            LV >= RV --> 변수(LV)의 type이 대입할 데이터(RV)보다 커야됨 (소수를 int에 대입 불가)
        */
        int intVar3 = 10;
        intVar3 = 10 + 20;  // intVar3 = add(10, 20) 가능 / intVar3 = 123.235 : 불가능 (double을 int에 대입 불가)
        System.out.println(intVar3);  // 출력: 30

        /*
         아래는 오류: 변수명 없이 직접 타입만으로 초기화 불가 (변수 선언 필요)
         byte = 1;  // 오류: 변수명 없음, byte는 리터럴 없이 변수로 선언해야 함
         short = 20; // 오류: 변수명 없음, short도 리터럴 없음 (기본 int로 인식됨)

         올바른 예:
         byte byteVar2 = 1;
         byteVar2 = byteVar2 + 10;  오류: byte + int(10) 연산 결과는 int로 자동 프로모션됨
                                    byte(1바이트)에 int(4바이트)를 대입하려면 캐스팅 필요 (예: byteVar2 = (byte)(byteVar2 + 10);)
                                    하지만 오버플로우 주의: byte 범위 초과 시 데이터 손실
        */

        // char 타입 (2바이트, 유니코드 문자 1개 저장, ' '로 감쌈)
        char charVar1 = 'e';  // char은 문자 1개만 담을 수 있는 타입임
        int intVar4 = 1;
        int intVar5 = '1';  // 문자 '1'의 아스키/유니코드 값은 49임

        System.out.println(charVar1);  // 출력: e
        System.out.println(intVar4);   // 출력: 1
        System.out.println(intVar5);   // 출력: 49

        /*
            실수형 (floating point)
            float(4바이트), double(8바이트)
            int도 float도 4바이트지만 float가 훨씬 큰 범위 표현 (지수부 때문)
            float는 double에 비해 메모리가 작아 오차가 자주 발생하므로 보통 double 사용
        */
        float floatVar = 4.5F;    // F 접미사 필수 (기본 실수 리터럴은 double)
        // float floatVar2 = 4.5; // 오류: 4.5는 double 리터럴로, float(4바이트)에 대입 불가 (double이 더 큰 타입)
                                  // 해결: 4.5F로 F 접미사 붙이거나 (float)4.5로 캐스팅
        double doubleVar = 4.5; // double은 기본 실수형, 접미사 생략 가능

        // 진위형 (boolean) true, false만 가능 (1바이트)
        boolean boolVar1 = true;
        boolVar1 = 45 > 15;             // 비교 연산 결과 (true/false) 대입
        System.out.println(boolVar1);   // 출력: true
        // true + true; // 오류: boolean은 산술 연산(+ 등) 불가, 논리 연산(&&, ||)만 가능

        System.out.println(0.1 + 0.2);          // 출력: 0.30000000000000004
        /*
        <0.3이 아닌 이유>
        10진수 소수(예: 0.1, 0.2, 0.3)를 2진수로 변환할 때, 일부 숫자는 무한소수가 됨.
        예:
            0.1 (10진수) = 0.0001100110011... (2진수, 무한 반복).
            0.2 = 0.001100110011... (무한 반복).
            0.3 = 0.010011001100... (무한 반복).
        double(64비트)에서 무한소수를 근사치로 저장하므로 오차 발생 (IEEE 754 표준 한계).
        */

        System.out.println((0.1 + 0.2) == 0.3); // 출력: false (오차로 인해 같지 않음)
        // System.out.println(10/0); // 오류: ArithmeticException (정수 0으로 나눔, 런타임 예외 발생)

        // 아래처럼 타입이 다른 숫자끼리 연산할 경우 자동 프로모션 (작은 타입 -> 큰 타입)
        // double / int ==> double로 변환
        // 0.1 / 0.0: 0.0은 진짜 0이 아닌 0에 가까운 아주 작은 수 (부동소수점 특성)
        // 따라서 0.1 / 0.0은 무한대(Infinity)로 출력됨
        System.out.println(0.1 / 0);            // 출력: Infinity (부동소수점에서 0 나눔 허용, 무한대 결과)

        // ※ casting: 프로모션의 반대, 개발자가 의도적으로 형변환
        // int intVar6 = (3.14 * 3.14 * 5); // 오류: (double * double * int) 결과는 double, int에 직접 대입 불가
        //                                 // 해결: 캐스팅으로 소수점 버림 (데이터 손실 가능)
        int intVar6 = (int)(3.14 * 3.14 * 5);
        System.out.println(intVar6);            // 출력: 49 (3.14*3.14=9.8596, *5=49.298, int 캐스팅으로 49)

        int intVar7 = 10;
        int intVar8 = 5;
        double doubleResult1 = intVar8 / intVar7;   // int / int = 0 (정수 나눗셈, 소수 버림), double로 프로모션 -> 0.0
        System.out.println(doubleResult1);          // 출력: 0.0

        double doubleResult2 = (double)intVar8 / intVar7;   // (double) 캐스팅으로 intVar8 -> double, intVar7도 프로모션 -> 0.5
        // (double)(intVar8 / intVar7) 은 0.0 출력: intVar8 / intVar7 먼저 연산(0) 후 캐스팅
        System.out.println(doubleResult2);          // 출력: 0.5
        /*
        result2 = (double)intVar8 / intVar7
        =       (14순위, 대입)
        /       (4순위, 나눗셈)
        캐스팅  (1순위, 캐스팅)
        총 3번의 연산. 연산자 우선순위: 캐스팅 > / > =
        */

        /*
        int a, b, c, d, e, f;
        f = 10;
        a = b = c = d = e = f;
        =는 우선순위가 같아 오른쪽에서 왼쪽으로 연산 (a = (b = (c = ...))) -> 정상 동작
        ppt 69p에 정리되어있음

        10 + 20 * 4; // * 우선 (20*4=80, +10=90)
        */

        // boolean boolVar2 = 10 - 2; // 오류: 10-2 결과는 int, boolean에 대입 불가 (boolean은 true/false만 가능)
        boolean boolVar2 = 10 > 2;     // 가능: 비교 연산 결과는 boolean

        byte byteVar3 = (byte)(127 + 1); // 출력: -128 (byte 범위 초과 오버플로우: 128 -> -128 순환)
        System.out.println(byteVar3);    // byte = -128 ~ 127

        // byte byteVar4;        // 오류: 초기화 안 된 변수 사용 불가
        // byteVar4 = byteVar4 + 1; // 오류: byteVar4 초기화 안 됨 + (byte + int) 결과 int -> byte 대입 불가

        char charVar2 = 'a';  // 97 (아스키/유니코드)    참고: " " (스페이스 공백은 32임)
        char charVar3 = 'A';  // 65
        System.out.println(charVar2);               // 출력: a
        System.out.println(charVar3);               // 출력: A
        System.out.println(charVar2 + 1);           // 출력: 98 (char + int -> int 프로모션)
        System.out.println(charVar2 > charVar3);    // 출력: true (97 > 65)

        System.out.println((char)(charVar2 + 1));   // 출력: b (int -> char 캐스팅)
        System.out.println(charVar2 - ' ');         // 출력: 65 (97 - 32, char - char -> int)
        System.out.println((char)(charVar2 - ' ')); // 출력: A (int -> char 캐스팅)

        // 문자열: 기본자료형 아님, 참조자료형 (String 클래스)
        // String 클래스 안에는 toString() 메서드가 오버라이드 되어 있어서
        // 출력하면 주소가 아닌 참조된 데이터가 출력됨
        String stringVar = "대한민국";
        System.out.println(stringVar);              // 출력: 대한민국
    }
}
