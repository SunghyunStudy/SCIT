// Student에서 봤을 때 studentTest 클래스는 외부 클래스임.
// 외부클래스에서 점수에 막!! 접근하면 안된다!

public class StudentTest {
    public static void main(String[] args) {
        Student boy1 = new Student();
        boy1.no = 1;            // Write, 정보를 전혀 보호하지 못함!
        boy1.name = "홍길동";
        boy1.it = 80;
        boy1.japan = 99;
        boy1.common = 100;

        System.out.printf("학번 : %d,  이름 : %s,  it : %d,  일본어 : %d,  종합 : %d", boy1.no, boy1.name, boy1.it, boy1.japan, boy1.common); // Read


        Student boy2 = new Student();
        boy2.no = 2;
        boy2.name = "임꺽정";
        boy2.it = 75;
        boy2.japan = 85;
        boy2.common = 70;
    }
}