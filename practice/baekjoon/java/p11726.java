
import java.io.*;

public class p11726 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        dp[1] = 1;

        if (N >= 2) {
            dp[2] = 2;
        }

        for (int i = 3; i <= N; i ++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        bw.write(dp[N] +"");
        bw.flush();
        bw.close();
    }
}
