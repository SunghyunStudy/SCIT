import java.util.Scanner;

// Arrays.sort(iary); 를 쓰면 배열이 알아서 오름차순으로 정렬됨

public class minMax_10818 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count, min, max;
        count = sc.nextInt();

        int[] iary = new int[count];

        for (int i = 0; i < iary.length; i++){
            iary[i] = sc.nextInt();
        }

        min = iary[0];
        max = iary[0];

        for(int j = 1; j < iary.length; j++){
            min = (min > iary[j]) ? iary[j] : min;
            max = (max < iary[j]) ? iary[j] : max;
        }

        System.out.print(min + " " + max);
    }
}
