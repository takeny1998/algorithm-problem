package algospot.java.POLY;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int MOD = 10_000_000;

    private static int C, N, cache[][];

    private static int poly(int n, int first) {
        if (n == first) {
            return 1;
        }

        if (cache[n][first] != -1) return cache[n][first];
        
        int ret = 0;

        for (int second = 1; second <= (n - first); second ++) {
            int add = (second + first - 1);
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }

        return cache[n][first] = ret;
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            cache = new int[N + 1][N + 1];

            for (int n = 0; n <= N; n ++) {
                Arrays.fill(cache[n], -1);
            }

            int answer = 0;

            for (int n = 1; n <= N; n ++) {
                answer += poly(N, n);
                answer %= MOD;
            }

            bw.write(answer + "\n");
        }

        bw.flush();
    }   

}