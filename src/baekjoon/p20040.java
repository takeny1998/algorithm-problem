package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p20040 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] parents, ranks;

    private static int N, M, a, b;
 
    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int a, int b) {
        a = find(a); b = find(b);

        if (a == b) return true;

        if (ranks[a] < ranks[b]) {
            int t = a; a = b; b = t;
        }
        parents[b] = a;

        if (ranks[a] == ranks[b]) {
            ranks[a] ++;
        }

        return false;
    } 

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        ranks = new int[N];

        for (int i = 0; i < N; i ++) {
            parents[i] = i;
        }

        int m = 1;
        int answer = 0;

        while (m <= M) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (answer == 0 && union(a, b)) {
                answer = m;
            }

            m ++;
        }

        
        bw.write(answer + "\n");
        bw.flush();
    }

}
