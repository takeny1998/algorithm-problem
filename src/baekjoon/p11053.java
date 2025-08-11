package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class p11053 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N;
    private static int[] dp, numbers;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;


        for (int curt = 1; curt < N; curt ++) {
            int max = 0;

            for (int prev = 0; prev < curt; prev ++) {
                if (numbers[prev] >= numbers[curt]) continue;
                max = Math.max(max, dp[prev]);
            }
            // 이전에 수열이 이어지는 경우 + 1, 아니면 1
            dp[curt] = max == 0 ? 1 : max + 1;

        }


        int result = dp[0];

        for (int i = 1; i < N; i ++) {
            result = Math.max(result, dp[i]);
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
