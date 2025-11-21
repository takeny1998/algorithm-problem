package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p9007 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int T, K, N, answer, absDiff;

    private static int[][] C, combs;

    private static void twoPointer() {
        int left = 0, right = (N * N) - 1;

        while (left < (N * N) && right >= 0) {
            int sum = combs[0][left] + combs[1][right];

            int abs = Math.abs(K - sum);

            if (abs <= absDiff) {
                if (abs == absDiff) {
                    answer = Math.min(answer, sum);
                } else {
                    answer = sum;
                }
                absDiff = abs;
            }

            if (sum == K) return;

            if (sum < K || right == 0) {
                left ++;
            }
            if (sum > K || left == (N * N)) {
                right --;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            answer = Integer.MAX_VALUE;
            absDiff = Integer.MAX_VALUE;
            
            st = new StringTokenizer(br.readLine());
            
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            
            C = new int[4][N];

            for (int i = 0; i < 4; i ++) {
                st = new StringTokenizer(br.readLine());
                for (int n = 0; n < N; n ++) {
                    C[i][n] = Integer.parseInt(st.nextToken());
                }
            }

            combs = new int[2][N * N];

            for (int n = 0; n < 2; n ++) {
                for (int i = 0; i < N; i ++) {
                    for (int r = 0; r < N; r ++) {
                        combs[n][(i * N) + r] = C[0 + (n * 2)][i] + C[1 + (n * 2)][r];
                    }
                }
                Arrays.sort(combs[n]);
            }

            twoPointer();
            bw.write(answer + "\n");
        }
        bw.flush();
    }

}
