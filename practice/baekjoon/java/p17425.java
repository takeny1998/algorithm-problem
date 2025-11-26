
import java.io.*;

public class p17425 {
    static int T, N;
    static final int VAL_MAX = 1000000;
    static long[] sum = new long[VAL_MAX + 1];
    static long[] dp = new long[VAL_MAX + 1];

    static void makeSum() {
        for (int n = 1; n <= VAL_MAX; n ++) {
            for (int nextN = n; nextN <= VAL_MAX; nextN += n) {
                sum[nextN] += n;
            }
            dp[n] = dp[n - 1] + sum[n];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        makeSum();
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            N = Integer.parseInt(br.readLine());
            bw.write(dp[N] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
