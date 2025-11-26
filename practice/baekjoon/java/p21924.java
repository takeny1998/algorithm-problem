
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p21924 {

    private static class Edge implements Comparable<Edge> {

        private final int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[] C, R;

    private static Edge[] E;

    private static int find(int v) {
        if (v == C[v]) return v;
        return C[v] = find(C[v]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (R[a] < R[b]) {
            int t = a;
            a = b;
            b = t;
        }
        C[b] = a;

        if (R[a] == R[b]) R[a] ++;
        return true;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = new int[N + 1];
        R = new int[N + 1];

        for (int i = 1; i <= N; i ++) {
            C[i] = i;
        }

        M = Integer.parseInt(st.nextToken());
        E = new Edge[M];

        long totalWeight = 0;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            E[i] = new Edge(a, b, c);
            totalWeight += c;
        }

        Arrays.sort(E);

        int picked = 0;
        long pickedWeight = 0;

        for (int i = 0; i < M; i ++) {
            if (picked == N - 1) break;

            int from = E[i].from;
            int to = E[i].to;
            int weight = E[i].weight;

            if (!union(from, to)) continue;

            picked ++;
            pickedWeight += weight;
        }


        if (picked != N - 1) {
            bw.write("-1\n");
        } else {
            bw.write((totalWeight - pickedWeight) + "\n");
        }

        bw.flush();
    }

}
