
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p5569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        long[][][][] dp = new long[h][w][2][2];

        for (int i = 0; i < w; i ++) dp[h - 1][i][0][0] = 1;
        for (int i = 0; i < h; i ++) dp[i][0][1][0] = 1;

        for (int y = (h - 2); y >= 0; y --) {
            for (int x = 1; x < w; x ++) {
                // 동쪽으로 1칸 이동해야 하는 경우
                // 이전에 동쪽에서 1칸온 경우의 수 + 2칸온 경우의 수
                dp[y][x][0][0] = (dp[y][x - 1][0][0] + dp[y][x - 1][0][1]) % 100000;
                // 동쪽으로 2칸 이동해야 하는 경우
                // 이전에 북쪽에서 1칸온 경우의 수
                dp[y][x][0][1] = dp[y][x - 1][1][0];

                dp[y][x][1][0] = (dp[y + 1][x][1][0] + dp[y + 1][x][1][1]) % 100000;
                dp[y][x][1][1] =  dp[y + 1][x][0][0];
            }
        }

        int answer = 0;
        for (int i = 0; i < 2; i ++)
            for (int r = 0; r < 2; r ++)
                answer += dp[0][w - 1][i][r];

        System.out.println(answer % 100000);
    }
}
