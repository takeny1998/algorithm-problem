package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class p17616 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static StringTokenizer st;

    private static int N, M, X, down, up;

    private static boolean[] visited;

    private static final Map<Integer, List<Integer>> edges = new HashMap<>();

    private static final Map<Integer, List<Integer>> reverseEdges = new HashMap<>();

    private static void dfs(int i) {
        if (visited[i]) return;
        visited[i] = true;

        if (i != X) up ++;
        
        for (int next : edges.getOrDefault(i, List.of())) {
            dfs(next);
        }
    }

    private static void reverseDfs(int i) {
        if (visited[i]) return;
        visited[i] = true;
        
        if (i != X) down ++;

        for (int next : reverseEdges.getOrDefault(i, List.of())) {
            reverseDfs(next);
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            List<Integer> edge = edges.getOrDefault(b, new ArrayList<>());
            edge.add(a);
            edges.put(b, edge);

            List<Integer> reverseEdge = reverseEdges.getOrDefault(a, new ArrayList<>());
            reverseEdge.add(b);
            reverseEdges.put(a, reverseEdge);
        }

        dfs(X);

        visited = new boolean[N + 1];
        reverseDfs(X);

        bw.write((up + 1) + " " + (N - down) + "\n");
        bw.flush();
    }

}
