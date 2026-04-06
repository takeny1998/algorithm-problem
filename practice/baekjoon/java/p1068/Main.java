package p1068;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, root, D;

    private static List<Integer>[] graph;

    private static int dfs(int i) {
        if (graph[i].isEmpty() || (graph[i].size() == 1 && graph[i].contains(D))) return 1;
        
        int c = 0;
        for (int r : graph[i]) {
            if (r == D) continue;
            c += dfs(r);
        }
        return c;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];

        for (int i = 0; i < N; i ++) {
            graph[i] = new ArrayList<>();   
        }
        
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            int p = Integer.parseInt(st.nextToken());

            if (p == -1) { root = i; continue; }
            graph[p].add(i);
        }

        D = Integer.parseInt(br.readLine());

        if (root == D) {
            bw.write("0\n");
        } else {
            bw.write(dfs(root) + "\n");
        }

        bw.flush();
    }

}
