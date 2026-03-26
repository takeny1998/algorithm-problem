package p11562;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[][] dp;

    private static int N, M, K, MAX = Integer.MAX_VALUE / 2 - 1;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        
        for (int n = 0; n <= N; n ++) {
            Arrays.fill(dp[n], MAX);
            dp[n][n] = 0;
        }

        M = Integer.parseInt(st.nextToken());
        for (int m = 0; m < M; m ++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[u][v] = 0;
            dp[v][u] = b == 0 ? 1 : 0;
        }

        for (int m = 1; m <= N; m ++) {
            for (int s = 1; s <= N; s ++) {
                if (m == s) continue;
                for (int e = 1; e <= N; e ++) {
                    if (s == e) continue;
                    dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m][e]);
                }
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k ++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            bw.write(dp[s][e] + "\n");
        }

        bw.flush();

    }

}
