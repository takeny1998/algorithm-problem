package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p1660 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final List<Integer> nodes = new ArrayList<>();

    private static final List<Integer> sums = new ArrayList<>();

    private static int N;

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        int num = 0;

        for (int i = 1; num <= 300000; i ++) {
            nodes.add(num += i);
        }

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        int sum = 0;
        for (int i = 0; i < nodes.size(); i ++) {
            if (sum + nodes.get(i) > N) break;
            sums.add(sum += nodes.get(i));
        }

        dp[0] = 0;
        for (int n = 1; n <= N; n ++) {
            for (int node : sums) {
                if (node > n) continue;
                dp[n] = Math.min(dp[n], dp[n - node] + 1);
            }
        }
        
        bw.write(dp[N] + "\n");
        bw.flush();
    }
    
}
