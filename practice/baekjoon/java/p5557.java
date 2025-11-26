
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p5557 {
    static int N;
    static int[] arr = new int[101];
    static long[] dp = new long[21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[arr[0]] = 1;

        for (int i = 1; i < (N - 1); i ++) {
            long[] temp = new long[21];

            for (int r = 0; r <= 20; r ++) {
                if (dp[r] != 0) {
                    if ((r - arr[i]) >= 0) temp[r - arr[i]] += dp[r];
                    if ((r + arr[i]) <= 20) temp[r + arr[i]] += dp[r];
                }
            }
            dp = temp.clone();
        }
        System.out.println(dp[arr[N - 1]]);
    }
}
