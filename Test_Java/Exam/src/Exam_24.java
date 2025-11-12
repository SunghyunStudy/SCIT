public class Exam_24 {
    public static void main(String[] args) {
        int exp[] = new int[5];
        int result[][] = new int[4][5];
        int row = 0, column, sum = 0;

        while(row < 5){
            column = 0;
            while(column < 5){
                if(row == 0){
                    exp[column] = column + 1;
                    System.out.printf("%7d",exp[column]);
                }
                else{
                    row--;
                    if(column == 0){
                        sum = (row+2) * (column+1);
                    }
                    else{
                        sum = sum * (row+2);
                    }
                    result[row][column] = sum;
                    System.out.printf("%7d", result[row][column]);
                    row++;
                }
                column++;
            }
            System.out.println();
            row++;
        }
    }
}