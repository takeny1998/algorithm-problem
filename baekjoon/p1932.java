import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1932 {

    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 1; r <= i; r ++) {
                map[i][r] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= y; x ++) {
                dp[y][x] = map[y][x] + Math.max(dp[y - 1][x - 1], dp[y - 1][x]);
            }
        }

        int max = Integer.MIN_VALUE;

        for (int x = 1; x <= N; x ++) {
            max = Math.max(max, dp[N][x]);
        }

        System.out.println(max);
    }
}
