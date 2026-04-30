package algospot.java.JUMPGAME;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N;

    private static int[][] matrix, dp;  

    private static int jump(int y, int x) {
        if (y >= N || x >= N) return 0;
        if (y == (N - 1) && x == (N - 1)) return 1;

        if (dp[y][x] != -1) return dp[y][x];

        int d = matrix[y][x];

        dp[y][x] = (((jump(y + d, x) != 0) || (jump(y, x + d) != 0)) ? 1 : 0);
        return dp[y][x];
    }

    public static void main(String[] args) throws Exception {
        
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            matrix = new int[N][N];
            dp = new int[N][N];

            for (int y = 0; y < N; y ++) {
                Arrays.fill(dp[y], -1);

                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x ++) {
                    matrix[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write((jump(0, 0) == 1 ? "YES" : "NO") + "\n");
        }
        bw.flush();
        br.close();
    }
}
