package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9252 {
    static int[][] dp, origin;
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
        origin = new int[lenB + 1][lenA + 1];

        int fromT = 1, fromL = 2, equal = 3;

        for (int b = 1; b <= lenB; b ++) {
            for (int a = 1; a <= lenA; a ++) {
                int left = dp[b][a - 1];
                int top = dp[b - 1][a];
                int leftTop = dp[b - 1][a - 1];

                if (top >= left) {
                    dp[b][a] = top;
                    origin[b][a] = fromT;
                } else {
                    dp[b][a] = left;
                    origin[b][a] = fromL;
                }

                if (A[a].equals(B[b])) {
                    if (dp[b][a] < leftTop + 1) {
                        dp[b][a] = leftTop + 1;
                        origin[b][a] = equal;
                    }
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int a = lenA, b = lenB;

        while (a >= 1 && b >= 1) {
            if (origin[b][a] == fromL) a --;

            if (origin[b][a] == fromT) b --;

            if (origin[b][a] == equal) {
                lcs.append(A[a]);
                a --; b--;
            }
        }

        System.out.println(lcs.length());
        System.out.println(lcs.reverse());
    }
}
