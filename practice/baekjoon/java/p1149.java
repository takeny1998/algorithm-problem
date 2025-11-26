
import java.io.*;
import java.util.StringTokenizer;

public class p1149 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + r;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + g;
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + b;
        }

        bw.write(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])) + "");
        bw.flush();
        bw.close();

    }

}
