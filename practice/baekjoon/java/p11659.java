
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11659 {
    static int[] arr;
    static long[] dp;
    static int N, M;

    static long findPartSum(int left, int right) {
        return dp[right] - dp[left - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        dp = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i ++) {
            dp[i] = dp[i - 1] + arr[i];
        }

        int I, J;
        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            J = Integer.parseInt(st.nextToken());
            sb.append(findPartSum(I, J) + "\n");
        }

        System.out.println(sb);
    }
}
