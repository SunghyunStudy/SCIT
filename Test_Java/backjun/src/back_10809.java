import java.util.Scanner;

public class back_10809 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] iary = new int[26];
        String str = sc.next();

        for(int i = 0; i < str.length(); i++){
            if(iary[str.charAt(i) - 'a'] != 0) continue;
            iary[str.charAt(i) - 'a'] += i + 1;
        }

        for(int i = 0; i < iary.length; i++){
            System.out.printf("%d ", iary[i] - 1);
        }
    }
}