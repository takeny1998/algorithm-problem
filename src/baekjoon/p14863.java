package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p14863 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] wWalk, wCycle, vWalk, vCycle;

    private static int[][] dp;

    private static int N, K;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wWalk = new int[N + 1]; 
        vWalk = new int[N + 1];

        wCycle = new int[N + 1]; 
        vCycle = new int[N + 1];

        for (int i = 1; i <= N; i ++) {
            st = new StringTokenizer(br.readLine());

            wWalk[i] = Integer.parseInt(st.nextToken());
            vWalk[i] = Integer.parseInt(st.nextToken());

            wCycle[i] = Integer.parseInt(st.nextToken());
            vCycle[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];
        for (int n = 0; n <= N; n++) Arrays.fill(dp[n], -1);
        dp[0][0] = 0;


        for (int n = 1; n <= N; n ++) {
            for (int k = 0; k <= K; k ++) {
                if (dp[n - 1][k] == -1) continue;

                if (k + wWalk[n] <= K) {
                    dp[n][k + wWalk[n]] = Math.max(dp[n][k + wWalk[n]], dp[n - 1][k] + vWalk[n]);
                }
                if (k + wCycle[n] <= K) {
                    dp[n][k + wCycle[n]] = Math.max(dp[n][k + wCycle[n]], dp[n - 1][k] + vCycle[n]);
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= K; i ++) {
            answer = Math.max(answer, dp[N][i]);
        }

        bw.write(answer + "\n");
        bw.flush();

    }

}
