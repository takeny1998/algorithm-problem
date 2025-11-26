
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1976 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int[][] dists;

    private static boolean[][] edges;

    private static int[] wants;

    private static int N, M;

    private static final int MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        edges = new boolean[N][N];
        dists = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dists[i], MAX_VALUE);
        }

        M = Integer.parseInt(br.readLine());
        wants = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i ++) {
            wants[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < N; i++) {
            for (int r = 0; r < N; r++) {
                if (i == r) dists[i][r] = 0;

                if (edges[i][r]) {
                    dists[i][r] = 1;
                }
            }
        }

        for (int middle = 0; middle < N; middle++) {

            for (int start = 0; start < N; start++) {
                if (start == middle) continue;

                for (int end = 0; end < N; end++) {
                    if (start == end) continue;
                    dists[start][end] = Math.min(dists[start][end], dists[start][middle] + dists[middle][end]);
                }
            }
        }

        boolean canGo = true;

        for (int i = 0; i < M - 1; i ++) {
            if (dists[wants[i]][wants[i + 1]] == MAX_VALUE) {
                canGo = false;
                break;
            }
        }

        if (canGo) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }

        bw.flush();
    }

}
