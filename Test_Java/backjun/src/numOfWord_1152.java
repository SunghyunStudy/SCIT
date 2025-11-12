import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class numOfWord_1152 {
    public static void main(String[] args) throws IOException {

        // 이 방법은 아예 비어있는 공백을 입력했을 경우에도 숫자를 새서 1로 출력될 가능성이 존재
        // 아래 방법을 사용하고 싶다면 하기의 모습처럼 비어있는 문자열의 경우 0을 출력하게끔 만들어야함.....
        /*
            if(s.trim().inEmpty){
                return 0;

         */


//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        String[] sary = s.trim().split(" ");
//
//        System.out.println(sary.length);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        System.out.print(st.countTokens());

    }
}
