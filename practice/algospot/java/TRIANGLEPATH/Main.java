package algospot.java.TRIANGLEPATH;
    
import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N, triangle[][], dp[][];

    private static int bruteForce(int y, int x, int sum) {
        if (y == N - 1) {
            return sum + triangle[y][x];
        }
        sum += triangle[y][x];
        return Math.max(bruteForce(y + 1, x, sum), bruteForce(y + 1, x + 1, sum));
    }

    private static int memoization(int y, int x) {
        if (y == N - 1) {
            return triangle[y][x];
        }
        if (dp[y][x] != -1) return dp[y][x];
        return dp[y][x] = triangle[y][x] 
                            + Math.max(memoization(y + 1, x), memoization(y + 1, x + 1));
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            triangle = new int[N][];
            dp = new int[N][];

            for (int y = 0; y < N; y ++) {
                triangle[y] = new int[y + 1];
                dp[y] = new int[y + 1];

                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < y + 1; x ++) {
                    triangle[y][x] = Integer.parseInt(st.nextToken());
                    dp[y][x] = -1;
                }
            }
            bw.write(memoization(0, 0) + "\n");
        }
        bw.flush();
    }

}
