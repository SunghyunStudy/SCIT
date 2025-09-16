public class DoubleLoop_01 {
    public static void main(String[] args){
        int i = 0, j = 0;

        while(i < 10){
            j = 0;
            System.out.print("i = " + i + ", j = " );
            while(j < 5){
                System.out.print(j + "  ");
                ++j;
            }

            ++i;
            System.out.println();
        }
    }

}
