package p12678;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] A, dp;

    private static int N, tail;

    private static int lowerBound(int a) {
        int left = 0, right = tail;

        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid] < a) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        
        A = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n ++) {
            A[n] = Integer.parseInt(st.nextToken());
        }

        dp[0] = A[0];
        tail = 0;
        for (int i = 1; i < N; i ++) {
            if (A[i] > dp[tail]) {
                dp[++ tail] = A[i];
                continue;
            }
            dp[lowerBound(A[i])] = A[i];
        }
        bw.write((tail + 1) + "\n");
        bw.flush();
    }
}
