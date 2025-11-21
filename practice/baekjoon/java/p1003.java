package baekjoon;

import java.io.*;

public class p1003 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int T, N;

    private static int[][] dp = new int[41][2];

    public static void main(String[] args) throws IOException {

        dp[0][0] = 1;
        dp[0][1] = 0;

        dp[1][0] = 0;
        dp[1][1] = 1;

        dp[2][0] = 1;
        dp[2][1] = 1;

        dp[3][0] = 1;
        dp[3][1] = 2;

        for (int n = 4; n <= 40; n ++) {
            dp[n][0] = dp[n - 1][0] + dp[n - 2][0];
            dp[n][1] = dp[n - 1][1] + dp[n - 2][1];
        }

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i ++) {
            N = Integer.parseInt(br.readLine());
            bw.write(dp[N][0] + " " + dp[N][1] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
