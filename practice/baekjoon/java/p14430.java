package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p14430 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[][] dp;

    private static int[][] field;

    private static int N, M;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        field = new int[N + 1][M + 1];

        for (int y = 1; y <= N; y ++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 1; x <= M; x ++) {
                field[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= M; x ++) {
                dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]) + field[y][x];
            }
        }

        bw.write(dp[N][M] + "\n");
        bw.flush();
    }

}