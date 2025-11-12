import java.util.Scanner;

public class back_3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] remain = new int[41];
        int temp;
        int count = 0;

        for(int i = 0; i < 10; i++){
            temp = sc.nextInt() % 42;
            remain[temp] += 1;
        }

        for (int i = 0; i < remain.length; i++){
            if(remain[i] != 0){
                count++;
            }
        }
        System.out.println(count);


    }
}
