package baekjoon;

import java.io.*;

public class p9095 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] dp = new int[12];


    public static void main(String[] args) throws IOException {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 12; i ++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i ++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
