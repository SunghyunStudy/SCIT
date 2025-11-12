import java.util.Scanner;

// 이거 다시 풀어보자 왜 그 생각을 못했지... 후우...

public class back_2920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] iary = new int[8];

        String result = "";

        for(int i = 0; i < iary.length; i++){
            iary[i] = sc.nextInt();
        }

        for(int i = 0; i < iary.length - 1; i++){
            if(iary[i + 1] == iary[i] + 1){
                result = "ascending";
            }
            else if (iary[i + 1] == iary[i] - 1){
                result = "descending";
            }
            else{
                result = "mixed";
                break;
            }
        }

        System.out.println(result);
    }
}
