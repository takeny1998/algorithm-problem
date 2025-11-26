
import java.io.*;
import java.util.*;

public class p1939 {

    private static class Edge {

        private int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static StringTokenizer st;

    private static int N, M, S, E, answer = 0;

    private static final List<List<Edge>> graph = new ArrayList<>();

    private static boolean[] visited;

    private static void dfs(int curt, int high) {
        if (curt == E) return;

        for (Edge edge : graph.get(curt)) {
            if (high > edge.weight || visited[edge.to]) continue;

            visited[edge.to] = true;
            dfs(edge.to, high);
        }
    }

    private static void parametricSearch() {

        int low = 1, high = 1000000000;

        while (low <= high) {
            int mid = (low + high) / 2;

            visited = new boolean[N + 1];
            visited[S] = true;
            dfs(S, mid);

            if (visited[E]) {
                answer = Math.max(answer, mid);
                low = mid + 1;
            }
            else high = mid - 1;
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i ++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parametricSearch();

        bw.write(answer + "\n");
        bw.flush();
    }

}
