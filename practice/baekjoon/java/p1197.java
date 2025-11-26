
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1197 {

    private static class Edge implements Comparable<Edge> {

        private final int from, to, weight;

        Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int V, E, A, B, C, answer;

    private static Edge[] edges;

    private static int[] parents, ranks;

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

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];

        for (int i = 1; i <= V; i ++) {
            parents[i] = i;
        }

        ranks = new int[V + 1];

        edges = new Edge[E];

        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(A, B, C);
        }

        Arrays.sort(edges);

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) continue;
            answer += edge.weight;
        }

        bw.write(answer + "\n");
        bw.flush();
    }


}
