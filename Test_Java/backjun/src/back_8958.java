import java.util.Scanner;

public class back_8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iter = sc.nextInt();


        for(int i = 0; i < iter; i++){
            String ox = sc.next();
            int result = 0;
            int plus = 0;
            for(int j = 0; j < ox.length(); j++){
                if(ox.charAt(j) == 'O'){
                    result = result + 1 + plus;
                    plus++;
                }else {
                    plus = 0;
                    continue;
                }
            }
            System.out.println(result);
        }
    }
}
