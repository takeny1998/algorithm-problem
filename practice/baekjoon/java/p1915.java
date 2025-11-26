
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1915 {
    static int[][] matrix, dp;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int y = 1; y <= N; y ++) {
            String[] line = br.readLine().split("");

            for (int x = 1; x <= line.length; x ++) {
                matrix[y][x] = Integer.parseInt(line[x - 1]);
            }
        }

        int maxSize = 0;

        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= M; x ++) {
                if (matrix[y][x] == 1) {
                    dp[y][x] = Math.min(dp[y - 1][x - 1], Math.min(dp[y - 1][x], dp[y][x - 1])) + matrix[y][x];
                    maxSize = Math.max(maxSize, dp[y][x]);
                }
            }
        }

        System.out.println(maxSize * maxSize);
    }
}
