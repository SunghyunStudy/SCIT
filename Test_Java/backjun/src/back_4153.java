import java.util.Scanner;

public class back_4153 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c, temp;

        while(true){
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            for(int i = 0; i < 3; ++i){
                if ( a > b ){
                    temp = a;
                    a = b;
                    b = temp;
                }

                if ( b > c ){
                    temp = b;
                    b = c;
                    c = temp;
                }

                if ( a > c ){
                    temp = a;
                    a = c;
                    c = temp;
                }
            }

            if(a == 0 && b == 0 && c == 0)break;
            else if(a*a + b*b == c*c) System.out.println("right");
            else System.out.println("wrong");
        }
    }
}
