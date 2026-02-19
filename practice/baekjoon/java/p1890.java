import java.io.*;
import java.util.*;

public class p1890 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static long[][] dp;

    private static int[][] matrix;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        dp = new long[N][N];
        matrix = new int[N][N];

        for (int y = 0; y < N; y ++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x ++) {
                matrix[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < N; x ++) {
                int k = matrix[y][x];
                if (k == 0) break;
                if ((x + k) < N) dp[y][x + k] += dp[y][x];
                if ((y + k) < N) dp[y + k][x] += dp[y][x];
            }
        }

        bw.write(dp[N - 1][N - 1] + "\n");
        bw.flush();
    }
}
