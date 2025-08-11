package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p7579 {
    static int N, M;
    static int[] cost, usage;
    static int answer = Integer.MAX_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        usage = new int[N + 1];
        cost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            usage[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int totalCost = 0;

        for (int i = 1; i <= N; i ++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        dp = new int[N + 1][totalCost + 1];

        for (int n = 1; n <= N; n ++) {
            for (int c = 0; c <= totalCost; c ++) {
                if (c >= cost[n])
                    dp[n][c] = Math.max(dp[n - 1][c], dp[n - 1][c - cost[n]] + usage[n]);
                else dp[n][c] = dp[n - 1][c];

                if (dp[n][c] >= M) answer = Math.min(answer, c);
                System.out.print(dp[n][c] + " ");
            }
            System.out.println();
        }

        System.out.println(answer);
    }
}
