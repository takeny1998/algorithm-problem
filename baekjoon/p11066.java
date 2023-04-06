import java.io.*;
import java.util.StringTokenizer;

public class p11066 {
    static final int INF = Integer.MAX_VALUE;
    static int[][] dp;
    static int[] num, sum;
    static int T, K;

    static int getSumInRange(int start, int end) {
        return sum[end] - sum[start - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t ++) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            dp = new int[K + 1][K + 1];
            num = new int[K + 1];
            sum = new int[K + 1];

            sum[1] = Integer.parseInt(st.nextToken());
            for (int i = 2; i <= K; i ++)
                sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());


            for (int range = 2; range <= K; range ++) {
                for (int start = 1; ; start ++) {
                    int end = start + range - 1;
                    if (end > K) break;

                    int minPoint = INF;
                    for (int splitPoint = start; splitPoint < end; splitPoint ++) {
                        minPoint = Math.min(minPoint,
                                dp[start][splitPoint] + dp[splitPoint + 1][end] + getSumInRange(start, end));
                    }
                    dp[start][end] = minPoint;
                }
            }

            bw.write(dp[1][K] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
