import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class p17404 {

    private static int N;
    private static int[][] rgb, dp;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static final int MAX_VALUE = 1000 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        rgb = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());

            for (int s = 0; s < 3; s ++)
                rgb[i][s] = Integer.parseInt(st.nextToken());
        }

        int answer = MAX_VALUE;

        for (int k = 0; k < 3; k ++) {

            for(int i = 0; i < N; i ++)
                Arrays.fill(dp[i], MAX_VALUE);

            dp[0][k] = rgb[0][k];

            for (int i = 1; i < N; i ++) {
                dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
            }

            for (int i = 0; i < 3; i ++) {
                if (i == k) continue;
                answer = min(answer, dp[N - 1][i]);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
}
