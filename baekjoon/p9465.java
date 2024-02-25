import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p9465 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int T, N;
    private static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t ++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[2][N + 1];
            dp = new int[2][N + 1];

            for (int i = 0; i < 2; i ++) {
                st = new StringTokenizer(br.readLine());

                for (int r = 1; r < N + 1; r ++)
                    arr[i][r] = Integer.parseInt(st.nextToken());
            }

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];

            for (int i = 2; i < N + 1; i ++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }

            int answer = Math.max(dp[0][N], dp[1][N]);

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

}
