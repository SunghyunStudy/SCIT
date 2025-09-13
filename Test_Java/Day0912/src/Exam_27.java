/*
[문제-27] 알파벳과 코드값 출력
알파벳 ‘A’ ~ ‘z’까지 해당 문자와 코드값을 모두 출력하시오
1) 주의
• 대문자 알파벳과 소문자 알파벳 사이의 특수문자는 출력되면 안된다.
• 연산자와 단일 for문, continue 사용할 것
3) 실행결과
A : 65
B : 66
C : 67
D : 68
E : 69
...
W : 87
X : 88
Y : 89
Z : 90
a : 97
b : 98
c : 99
...
x : 120
y : 121
z : 122
 */

/*
유니코드는 16비트
2바이트로 이루어진 16비트

 */
public class Exam_27 {
    public static void main(String[] args) {
        for(int i = 65; i < 123; i++){
            if ( i > 90 && i < 97) continue;
            System.out.printf("%c : %d%n",(char)i, i);
        }
    }
}

/*
public class Exam_27 {
    public static void main(String[] args) {
        System.out.printf("%c : %d, %c : %d, %c : %d, %c : %d ", 'A', (int)'A', 'Z', (int)'Z', 'a', (int)'a', 'z', (int)'z');

        for(char c = 'A'; c <= 'z'; c++)
            if(!((c >= 'A' && c<='Z') || (c>='a' && c<='z')) continue;

    }

}
*/
