package algospot.java.PICNIC;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N, M;

    private static boolean[] visited;

    private static boolean[][] graph;

    private static int dfs() {
        int n1 = Integer.MAX_VALUE;

        for (int n = 0; n < N; n ++) {
            if (!visited[n]) {
                n1 = n; break;
            }
        }

        if (n1 == Integer.MAX_VALUE) return 1;
        int r = 0;

        for (int n2 = n1 + 1; n2 < N; n2 ++) {
            if (n1 == n2 || visited[n1] || visited[n2]) continue;
            if (!graph[n1][n2] && !graph[n2][n1]) continue;
            visited[n1] = true; visited[n2] = true;
            r += dfs();
            visited[n1] = false; visited[n2] = false;
        }
        return r;
    }
    
    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            visited = new boolean[N];
            graph = new boolean[N][N];

            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m ++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = true;
                graph[b][a] = true;
            }
            
            bw.write(dfs() + "\n");
        }
        bw.flush();
    }
}
