package baekjoon;

import java.io.*;
import java.util.*;

public class p15652 {
    
    private static final BufferedReader br = 
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = 
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static void dfs(String prefix, int prev, int depth) throws Exception {
        if (depth > M) {
            bw.write(prefix + "\n");
            return;
        }
        
        for (int next = prev; next <= N; next ++) {
            if (prefix.isBlank()) dfs(next + "", next, depth + 1);
            else dfs(prefix + " " + next, next, depth + 1);
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs("", 1, 1);
        bw.flush();
    }
}
