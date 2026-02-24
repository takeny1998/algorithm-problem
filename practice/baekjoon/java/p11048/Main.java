package p11048;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[][] matrix, dp;

    
    public static void main(String[] args) throws Exception {
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        matrix = new int[N][M];
        dp = new int[N][M];

        for (int n = 0; n < N; n ++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m ++) {
                matrix[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = matrix[0][0];

        for (int n = 0; n < N; n ++) {
            for (int m = 0; m < M; m ++) {
                if (n + 1 < N) {
                    dp[n + 1][m] = Math.max(
                        dp[n + 1][m], dp[n][m] + matrix[n + 1][m]);
                }
                if (m + 1 < M) {
                    dp[n][m + 1] = Math.max(
                        dp[n][m + 1], dp[n][m] + matrix[n][m + 1]);
                }
            }
        }

        bw.write(dp[N - 1][M - 1] + "\n");
        bw.flush();
    }
}
