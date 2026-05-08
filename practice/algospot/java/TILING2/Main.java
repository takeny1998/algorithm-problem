package algospot.java.TILING2;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int MOD = 1_000_000_007;

    private static int C, N, cache[];

    private static int dfs(int x) {
        if (x > N) return 0;
        if (x >= N) return 1;

        if (cache[x] != -1) return cache[x];
        
        return cache[x] = ((dfs(x + 1) + dfs(x + 2)) % MOD);
    }

    private static int dfs2(int w) {
        if (w <= 1) return 1;
        if (cache[w] != -1) return cache[w];
        
        return cache[w] = ((dfs2(w - 2) + dfs2(w - 1)) % MOD);
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            N = Integer.parseInt(br.readLine());
            
            // cache = new int[101];
            // Arrays.fill(cache, -1);
            // bw.write(dfs(0) + "\n");
            
            cache = new int[101];
            Arrays.fill(cache, -1);
            bw.write(dfs2(N) + "\n");
            
        }
        bw.flush();
    }
}
