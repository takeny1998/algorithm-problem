package p2294;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static final int MAX = 100_000 * 10_000 + 1;

    private static int N, K;

    private static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int K = Integer.parseInt(st.nextToken());
        dp = new int[100_001];
        Arrays.fill(dp, MAX);
        
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
            dp[arr[i]] = 1;
        }

        for (int k = 1; k <= K; k ++) {
            for (int i = 0; i < N; i ++) {
                if (arr[i] > k) continue;
                dp[k] = Math.min(dp[k], dp[k - arr[i]] + 1);
            }
        }

        if (dp[K] == MAX) {
            bw.write(-1 + "\n");
        } else {
            bw.write(dp[K] + "\n");
        }

        bw.flush();
    }
}
