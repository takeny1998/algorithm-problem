package p1311;


import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, W_MAX;

    private static int[] dp;

    private static int[][] matrix;

    private static int countBit(int n) {
        int numOfBit = 0;
        int p = 1;
        while (p <= n) {
            if ((n & p) != 0) numOfBit ++;
            p <<= 1;
        }
        return numOfBit;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        W_MAX = Integer.MAX_VALUE;

        dp = new int[(1 << N)];
        Arrays.fill(dp, W_MAX);

        matrix = new int[N][N];
        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 0; r < N; r ++) {
                matrix[i][r] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0] = 0;
        for (int m = 0; m < (1 << N); m ++) {
            if (dp[m] == W_MAX) continue;

            int i = countBit(m);
            if (i == N) continue;
            
            for (int r = 0; r < N; r ++) {
                if ((m & (1 << r)) != 0) continue;
                dp[m | (1 << r)] = Math.min(dp[m | (1 << r)], dp[m] + matrix[i][r]);
            }
        }

        bw.write(Arrays.toString(dp) + "\n");

        bw.write(dp[(1 << N) - 1] + "\n");
        bw.flush();
    }
}
