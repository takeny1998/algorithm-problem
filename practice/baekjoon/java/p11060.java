import java.io.*;
import java.util.*;

public class p11060 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static int[] A, dp;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        
        A = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = (Integer.MAX_VALUE) / 2;
        }

        dp[0] = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = i + 1; j <= (i + A[i]); j ++) {
                if (j == N) break;
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        if (dp[N - 1] == ((Integer.MAX_VALUE) / 2)) {
            bw.write("-1\n");
        } else {
            bw.write(dp[N - 1] + "\n");
        }
        bw.flush();
    }
}
