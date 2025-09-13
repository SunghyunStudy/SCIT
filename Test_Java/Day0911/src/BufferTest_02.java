import java.util.Scanner;

public class BufferTest_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name;
        int age;
        String address;

        System.out.print("이름 : ");
        name = sc.next();               // 홍길동(1) + 엔터(2)
        System.out.print("나이 : ");
        age = sc.nextInt();             // 엔터(2) + 20(3) + 엔터(4)     nextInt는 앞의 엔터는 무시하지만 뒤의 엔터는 남겨둠.

        sc.nextLine();                  // 엔터(4)를 소진시켜서 버퍼를 비워버림.
        System.out.print("주소 : ");
        address = sc.nextLine();

        System.out.println();

        System.out.printf("당신의 정보 : %s %d살 %s", name, age, address);

    }
}
