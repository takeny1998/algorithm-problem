package baekjoon;

import java.io.*;
import java.util.*;

public class p18116 {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, MAX = (10 * 10 * 10 * 10 * 10 * 10) + 1;

    private static int[] parents, ranks;

    private static int find(int x) {
        if (parents[x] == x) return x;

        return (parents[x] = find(parents[x]));
    }

    private static void union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return;

        if (ranks[b] > ranks[a]) {
            int t = a; a = b; b = t;
        }
        parents[b] = a;
        ranks[a] += ranks[b];
        ranks[b] = 0;
    } 

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        parents = new int[MAX];
        ranks = new int[MAX];

        for (int n = 1; n < MAX; n ++) {
            parents[n] = n;
            ranks[n] = 1;
        }

        for (int n = 0; n < N; n ++) {
            st = new StringTokenizer(br.readLine());

            String comm = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
                
            if (comm.equals("I")) {
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
                continue;
            }
            bw.write(ranks[find(a)] + "\n");
        }
        bw.flush();
    }
}
