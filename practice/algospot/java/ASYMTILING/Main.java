package algospot.java.ASYMTILING;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int MOD = 1_000_000_007;

    private static int C, N, cache[];

    private static int findEntireCase(int w) {
        if (w <= 1) return 1;
        if (cache[w] != -1) return cache[w];
        
        return cache[w] = (findEntireCase(w - 2) + findEntireCase(w - 1)) % MOD;
    }
    
    private static int findSymmetryCase(int w) {
        if (w == 2) return 2;

        if (w < 0) return 0;
        if (w == 0 || w == 1) return 1;


        if (cache[w] != -1) return cache[w];

        return cache[w] = (findSymmetryCase(w - 4) + findSymmetryCase(w - 2)) % MOD;
    }


    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            
            cache = new int[101];
            Arrays.fill(cache, -1);

            int entire = findEntireCase(N);

            cache = new int[101];
            Arrays.fill(cache, -1);

            bw.write(((entire - findSymmetryCase(N) + MOD) % MOD) + "\n");
        }
        bw.flush();
    }
}
