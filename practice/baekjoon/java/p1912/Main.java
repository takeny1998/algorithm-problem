package p1912;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];

        for (int i = 1; i < N; i ++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        int answer = -Integer.MAX_VALUE;

        for (int i = 0; i < N; i ++) {
            answer = Math.max(answer, dp[i]);
        }

        bw.write(answer + "\n");
        bw.flush();
    }
    
}
