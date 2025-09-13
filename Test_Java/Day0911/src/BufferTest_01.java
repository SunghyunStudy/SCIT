import java.io.IOException;
import java.util.Scanner;

public class BufferTest_01 {
    public static void main(String[] args) throws IOException {
//        int num = 0;
//        for(char ch = 'あ'; ch <= 'ゖ'; ch++){
//            ++num;
//            System.out.printf("%d : %c%n", num, ch);
//        }


//        for(int i = 0; i < 128; ++i){
//            System.out.printf("%d : %c%n", i, (char)i);
//        }

//        InputStream keyin = System.in;
//        while(true){
//            int data = keyin.read();    //.read()도 IOException 추가해줘야 함.
//            System.out.println(data);
//            // a를 입력하면
//            // 97
//            // 10
//            // 이 출력됨
//            // 10은 \n의 아스키코드임. 엔터를 쳤기 때문.
//        }

        Scanner sc = new Scanner(System.in);

        String str = null;
        while(true){
            System.out.print("글자를 입력하세요 : ");
            // str = sc.next();  // 공백, 띄어쓰기 등 공백문자를 기준으로 가져옴 aaa bbb ccc 를 입력 할 경우 aaa 먼저 비움.
            str = sc.nextLine(); // 얘는 입력 buffer의 모든 데이터를 다 가져옴. 싹 다 비움.


            System.out.println(str);
        }


    }
}
