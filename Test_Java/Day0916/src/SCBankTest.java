import java.time.LocalDate;

public class SCBankTest {
    public static void main(String[] args) {
        // output(keyin) -- void output(Scanner keyin) : 레퍼런스
        // output(weight) -- void output(double w)  : 값   8개의 타입(byte, short, int, long, float, double, char, boolean 은 무조건 call by value 이고 그 외는 call by reference
        // output(ch) -- void output(char[] c) : ch 주소(레퍼런스)
        // output(man1) -- void output(SCBank man)  : b1 주소 (레퍼런스)
        // String도 참조(레퍼런스)변수임
        // call by value는 스택영역에 생성된 변수에 값이 다이렉트로 저장됨


        SCBank man1 = new SCBank();   // man1 안에는 주소가 들어있는거임.
        man1.customerName = "홍길동";
        man1.accountNo = "m1234";
        man1.balance = 10000000;
        man1.password = "abc";
        man1.createDate = LocalDate.now();

        print(man1);        // man1을 부르려면 선언할때 어떤 타입인지 알아야됨 -> SCBank 타입임.
                            // 직접 생성한 객체 뿐만 아니라 int로 선언된것도 메서드를 만들때 int a 이런식으로 매개변수에 넣어줘야함.
                            // -> 예) int plus(int a, int b)





        SCBank man2 = new SCBank();
        man2.customerName = "손오공";
        man2.accountNo = "s5678";
        man2.balance = 5000000;
        man2.password = "def";
        man2.createDate = LocalDate.of(2025, 9, 6);


        print(man2);

    }
    public static void print(SCBank man){ // SCBank는 8개의 기본자료형이 아닌 참조자료형임.
        // SCBank man은 call 할 때만 생성되고 블록이 닫히면 메모리에서 지워짐.
        System.out.printf("고객명 : %s,  계좌번호 : %s,  잔액: %,d원, 비밀번호 : %s,  개설일 : %s%n",
                man.customerName, man.accountNo, man.balance, man.password, man.createDate);
    }
}
