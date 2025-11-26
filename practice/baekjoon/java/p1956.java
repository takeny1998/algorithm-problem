
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1956 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static long[][] dists;

    private static int V, E, a, b, c;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dists = new long[V + 1][V + 1];

        for (int i = 1; i <= V; i ++) {
            for (int r = 1; r <= V; r ++) {
                if (i == r) continue;
                dists[i][r] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < E; i ++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dists[a][b] = c;
        }

        for (int mid = 1; mid <= V; mid ++) {
            for (int start = 1; start <= V; start ++) {
                for (int end = 1; end <= V; end ++) {


                    dists[start][end] = Math.min(
                        dists[start][mid] + dists[mid][end], dists[start][end]);
                }
            }
        }

        long answer = Integer.MAX_VALUE;
        for (int start = 1; start <= V; start ++) {
            for (int end = 1; end <= V; end ++) {
                if (start == end) continue;
                answer = Math.min(answer, dists[start][end] + dists[end][start]);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        bw.write(answer + "\n");
        bw.flush();
    }
}
