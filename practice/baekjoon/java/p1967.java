package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1967 {
    
    private static class Edge {

        private final int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N;

    private static List<Edge>[] edges;

    private static Edge bfs(int start) {
        Queue<Edge> queue = new ArrayDeque<>();

        queue.add(new Edge(start, 0));

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        Edge maxEdge = queue.peek();

        while (!queue.isEmpty()) {
            Edge curt = queue.poll();
            
            if (curt.dist > maxEdge.dist) {
                maxEdge = curt;
            }
            
            for (Edge next : edges[curt.to]) {
                if (visited[next.to]) continue;
                visited[next.to] = true;

                queue.add(new Edge(next.to, curt.dist + next.dist));
            }
        }

        return maxEdge;
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        edges = new ArrayList[N + 1];

        for (int i = 0; i <= N; i ++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, dist));
            edges[to].add(new Edge(from, dist));
        }

        Edge leaf = bfs(1);
        Edge answer = bfs(leaf.to);

        bw.write(answer.dist + "\n");
        bw.flush();
    }
}
