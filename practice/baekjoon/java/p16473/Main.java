package p16473;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, T;

    private static int[] array;

    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        array = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n ++) {
            array[n] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[N + 2][N + 2];

        for (int i = 1; i <= N; i ++) {
            for (int r = N; r >= 1; r --) {
                if (i >= r || array[i] != array[r]) {
                    dp[i][r] = Math.max(dp[i - 1][r], dp[i][r + 1]);
                    continue;
                }
                dp[i][r] = dp[i - 1][r + 1] + 1;
            }
        }

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t ++) {
            int mid = Integer.parseInt(br.readLine());

            int lcs = dp[mid - 1][mid + 1];

            bw.write(((N - 1) - (lcs * 2)) + "\n");
        }

        bw.flush();
    }
    
}
