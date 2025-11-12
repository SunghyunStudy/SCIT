import java.util.Scanner;

public class back_2577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a, b, c, total, temp;
        int[] result = new int[10];
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        total = a * b * c;

        while(total != 0){
            temp = total % 10;
            result[temp] += 1;
            total = total / 10;
        }

        for(int i = 0; i < result.length; ++i){
            System.out.println(result[i]);
        }
    }
}
