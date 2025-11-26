
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p11727 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 4];

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 5;

        for (int n = 3; n <= N; n ++) {
            dp[n] = (dp[n - 1] + (2 * dp[n - 2])) % 10007;
        }

        bw.write(dp[N] + "\n");
        bw.flush();
    }

}
