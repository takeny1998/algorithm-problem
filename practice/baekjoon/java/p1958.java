package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p1958 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] A, B, C;

    private static int[][][] dp;

    public static void main(String[] args) throws Exception {
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        C = br.readLine().toCharArray();

        dp = new int[A.length + 1][B.length + 1][C.length + 1];

        for (int a = 1; a <= A.length; a ++) {
            for (int b = 1; b <= B.length; b ++) {
                for (int c = 1; c <= C.length; c ++) {
                    if ((A[a - 1] == B[b - 1]) && (B[b - 1] == C[c - 1])) {
                        dp[a][b][c] = dp[a - 1][b - 1][c - 1] + 1;
                    } else {
                        dp[a][b][c] = Math.max(
                            dp[a - 1][b][c], 
                            Math.max(dp[a][b - 1][c], dp[a][b][c - 1]));
                    }
                }
            }
        }

        bw.write(dp[A.length][B.length][C.length] + "\n");
        bw.flush();
    }

}
