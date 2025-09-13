import java.util.Scanner;

public class 저는빨강과녹색을구분못해요 {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static char map[][];
    static String arr[];
    static char now;	//현재 색깔

    static int dx[] = { 0, 0, -1, 1 };
    static int dy[] = { -1, 1, 0, 0 };

    static int visited[][];
    static int count1 = 0;	//일반인 구역
    static int count2 = 0;	//적록색약 구역

    public static void main(String[] args) {
        N = sc.nextInt();
        map = new char[N][N];
        arr = new String[N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.next();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = arr[i].charAt(j);	//String 받은거 배열에 char로 넣기
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    count1++;
                    dfs1(i, j);		//일반인 dfs
                }
            }
        }

        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    count2++;
                    dfs2(i, j);		//적록색약 dfs
                }
            }
        }

        System.out.println(count1 + " " + count2);
    }

    static void dfs1(int x, int y) { // 색약X
        visited[x][y] = 1;
        now = map[x][y];

        //R G B 다 구분 가능하기 때문에 따로 조건 필요 X
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (visited[nx][ny] == 0 && map[nx][ny] == now) {
                dfs1(nx, ny);
            }
        }
    }

    static void dfs2(int x, int y) { // 색약O
        visited[x][y] = 1;
        now = map[x][y];

        if (now != 'B') {		//R이나 G이면 dfs 도는 조건
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny] == 0 && map[nx][ny] != 'B') {
                    dfs2(nx, ny);
                }
            }
        }

        else {			//B이면 dfs 도는 조건
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny] == 0 && map[nx][ny] == now) {
                    dfs2(nx, ny);
                }
            }
        }
    }
}
