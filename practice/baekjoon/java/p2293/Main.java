package p2293;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, K;

    private static int[] coins;
    
    private static int[][] dp;

    private static int dfs(int i, int remain) {
        if (i == N || remain <= 0) {
            return remain == 0 ? 1 : 0;
        }
        return dfs(i, remain - coins[i]) + dfs(i + 1, remain);
    }

    public static void main(String[] args) throws Exception {


        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];
        

        coins = new int[N + 1];
        for (int i = 1; i <= N; i ++) {
            coins[i] = Integer.parseInt(br.readLine());
            
            for (int r = K; r >= 0; r -= coins[i]) {
                dp[i][r] = 1;
            }
        }

        for (int i = 2; i <= N; i ++) {
            for (int r = K; r >= 0; r --) {
                if (r + coins[i] > K) {
                    dp[i][r] = dp[i - 1][r];
                    continue;
                }
                dp[i][r] = dp[i - 1][r] + dp[i][r + coins[i]];
            }
        }

        bw.write(dp[N][0] + "\n");
        bw.flush();
    }
    
}
