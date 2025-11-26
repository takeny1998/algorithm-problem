
import java.io.*;
import java.util.*;

public class p17626 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];

        for (int i = 1; (i * i) <= n; i ++) {
            dp[i * i] = 1;
        }

        for (int i = 1; i <= n; i ++) {
            if (dp[i] != 0) continue;

            int min = Integer.MAX_VALUE;
            for (int j = 1; (j * j) <= i; j ++) {
                min = Math.min(min, dp[j * j] + dp[i - (j * j)]);
            }
            dp[i] = min;
        }

        bw.write(dp[n] + "\n");
        bw.flush();

    }
}
