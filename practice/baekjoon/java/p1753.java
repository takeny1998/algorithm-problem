
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1753 {

    static class Node implements Comparable<Node> {
        int vertex, weight;

        Node(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.weight, node.weight);
        }
    }

    static ArrayList<Node>[] map;
    static int[] dist;
    static int V, E, K;

    static void dijkstra() {
        PriorityQueue<Node> minQ = new PriorityQueue<>();
        dist[K] = 0;
        minQ.add(new Node(K, 0));

        while (!minQ.isEmpty()) {
            Node node = minQ.poll();

            for (Node nextNode : map[node.vertex]) {
                int nextDist = dist[node.vertex] + nextNode.weight;
                if (nextDist < dist[nextNode.vertex]) {
                    dist[nextNode.vertex] = nextDist;
                    minQ.add(new Node(nextNode.vertex, nextDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        map = new ArrayList[V + 1];
        dist = new int[V + 1];

        for (int i = 1; i <= V; i ++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[u].add(new Node(v, w));
        }

        for (int i = 1; i <= V; i ++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dijkstra();

        for (int i = 1; i <= V; i ++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

}
