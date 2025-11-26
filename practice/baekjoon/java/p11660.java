
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11660 {
    static int[][] arr;
    static long[][] dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        dp = new long[N + 1][N + 1];

        for (int y = 1; y <= N; y ++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x ++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= N; x ++) {
                dp[y][x] = arr[y][x] + dp[y][x - 1] + dp[y - 1][x] - dp[y - 1][x - 1];
            }
        }


        int y1, y2, x1, x2;
        long answer;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            y1 = Integer.parseInt(st.nextToken());
            x1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());

            answer = dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}
