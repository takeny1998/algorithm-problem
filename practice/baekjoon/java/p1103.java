
import java.util.Scanner;

public class p1103 {
    static int N, M, answer = 0;
    static boolean isLoop = false;
    static int[][] field, dp;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void back(int y, int x, int depth) {
        dp[y][x] = depth;
        answer = Math.max(answer, depth);

        for (int i = 0; i < 4; i ++) {
            int value = field[y][x];
            int next_y = y + (dy[i] * value);
            int next_x = x + (dx[i] * value);

            if ((next_y >= 0 && next_y < N) && (next_x >= 0 && next_x < M)
                && field[next_y][next_x] > 0) {

                if (!visited[next_y][next_x]) {
                    if (isLoop) return;
                    if (depth < dp[next_y][next_x]) continue;

                    visited[next_y][next_x] = true;
                    back(next_y, next_x, depth + 1);
                    visited[next_y][next_x] = false;
                } else {
                    isLoop = true;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        field = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i ++) {
            char[] line = sc.next().toCharArray();

            for (int r = 0; r < M; r ++) {
                if (line[r] == 'H') {
                    field[i][r] = 0;
                } else {
                    field[i][r] = line[r] - '0';
                }
            }
        }

        back(0, 0, 0);

        if (isLoop) {
            System.out.println(-1);
        } else {
            System.out.println(answer + 1);
        }
    }
}
