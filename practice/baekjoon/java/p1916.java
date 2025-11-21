package baekjoon;

import java.io.*;
import java.util.*;

public class p1916 {
    
    private static class Node implements Comparable<Node> {

        private int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.dist, node.dist);
        }

    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static List<List<Node>> graph = new ArrayList<>();

    private static int[] dijkstra(int start) {

        Queue<Node> queue = new PriorityQueue<>();

        int[] dists = new int[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N + 1];

        queue.add(new Node(start, 0));
        dists[start] = 0;

        while (!queue.isEmpty()) {
            Node curt = queue.poll();

            if (visited[curt.to]) continue;
            visited[curt.to] = true;

            for (Node next : graph.get(curt.to)) {
                int dist = curt.dist + next.dist;

                if (dist >= dists[next.to]) continue;
                
                dists[next.to] = dist;
                queue.add(new Node(next.to, dist));
            }
        }

        return dists;
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i ++) {
            graph.add(new ArrayList<>());
        }
        
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        int[] dists = dijkstra(start);

        bw.write(dists[to] + "\n");
        bw.flush();
    }
}
