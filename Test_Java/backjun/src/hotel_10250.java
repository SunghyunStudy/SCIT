import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class hotel_10250 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0, col, raw, client;

        count = sc.nextInt();

        for (int i = 0; i < count; i++){
            raw = sc.nextInt();
            col = sc.nextInt();
            client= sc.nextInt();

            int result = calc(col, raw, client);
            System.out.println(result);
        }
    }

    public static int calc(int col, int raw, int client){
        int floor, roomNum, roomCnt = 0;

        int[][] iary = new int[col][raw];

        for(int j = 0; j < iary.length; j++){
            roomNum = j + 1;
            for(int k = 0; k < iary[j].length; k++){
                floor = (k+1) * 100;
                roomCnt++;

                if(roomCnt == client){
                    return roomNum + floor;
                }
            }
        }
        return 0;
    }
}
