import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int R;
    public static int C;
    public static int N;
    public static char[][] map;
    public static int[][] time;

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        time = new int[R][C];

        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
//                if (map[r][c] == 'O') {
//                    time[r][c] = 1;
//                }
            }
        }
        // 1초부터 시작해서 N초 까지 반복
        for (int t = 1; t <= N; t++) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    time[r][c]++;
                }
            }
            // 폭탄이 설치 되어 있지 않은 모든 칸에 폭탄 설치.
            if (t % 2 == 0) {
                installBomb();
            } 
            // 설치 해둔 폭탄 중 폭발할 수 있는 폭탄 폭발
            else if (t > 1 && t % 2 == 1) {
                explode();
            }

        }

        for (char[] ca : map) {
            for (char c : ca) {
                System.out.print(c);
            }
            System.out.println();
        }

    }



    private static void installBomb() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == '.') {
                    map[r][c] = 'O';
                    time[r][c] = 0;
                }
            }
        }
    }

    private static void explode() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (time[r][c] == 3) {
                    map[r][c] = '.';
                    time[r][c] = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr >= 0 && nc >= 0 && nr < R && nc < C && time[nr][nc] != 3) {
                            map[nr][nc] = '.';
                            time[nr][nc] = 0;
                        }
                    }
                }
            }
        }


    }
}
