
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p12865 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, K;
    private static int[][] dp;
    private static int[] values, weights;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];
        values = new int[N + 1];
        weights = new int[N + 1];

        for (int i = 1; i <= N; i ++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i ++) {
            int weight = weights[i];
            int value = values[i];

            for (int k = 1; k <= K; k ++) {
                if (weight > k) {
                    dp[i][k] = dp[i - 1][k];
                } else {
                    dp[i][k] = Math.max(dp[i - 1][k], value + dp[i - 1][k - weight]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
