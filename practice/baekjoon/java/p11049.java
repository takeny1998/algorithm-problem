package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class p11049 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int[][] dp, matrix;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        matrix = new int[N][2];
        dp = new int[N][N];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());

            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int iter = 0; iter < (N - 1); iter ++) {

            for (int i = 0; i < (N - 1) - iter; i ++) {
                int j = (i + iter) + 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k ++) {
                    // 두 행렬을 곱하면 경우의 수를 알 수 있다.
                    int matValue = matrix[i][0] * matrix[k][1] * matrix[j][1];
                    // 행렬을 곱해서 더하는 경우와 이전 경우의 수를 비교한다.
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + matValue);
                }
            }
        }

        bw.write(dp[0][N - 1] + "");
        bw.flush();
        bw.close();
    }
}
