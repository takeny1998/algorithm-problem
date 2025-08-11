package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p5582 {
    static int[][] dp;
    static String[] A, B;
    static int lenA, lenB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split("");
        lenA = line.length;
        A = new String[lenA + 1];
        for (int i = 1; i <= lenA; i ++) {
            A[i] = line[i - 1];
        }

        line = br.readLine().split("");
        lenB = line.length;
        B = new String[lenB + 1];
        for (int i = 1; i <= lenB; i ++) {
            B[i] = line[i - 1];
        }

        dp = new int[lenB + 1][lenA + 1];
        int max = 0;

        for (int b = 1; b <= lenB; b ++) {
            for (int a = 1; a <= lenA; a ++) {

                if (A[a].equals(B[b])) {
                    int leftTop = dp[b - 1][a - 1];
                    dp[b][a] = Math.max(dp[b][a], leftTop + 1);
                    max = Math.max(max, dp[b][a]);
                }
            }
        }

        System.out.println(max);
    }
}
