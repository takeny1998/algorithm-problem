import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2458 {
    static int N, M, answer = 0;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][N + 1];

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int tall = Integer.parseInt(st.nextToken());

            matrix[small][tall] = 1;
        }

        for (int pass = 1; pass <= N; pass ++) {
            for (int from = 1; from <= N; from ++) {
                for (int to = 1; to <= N; to ++) {
                    if (from != to && matrix[from][pass] == 1 && matrix[pass][to] == 1) {
                        matrix[from][to] = 1;
                    }
                }
            }
        }

        for (int curt = 1; curt <= N; curt ++) {
            int taller = 0;
            int smaller = 0;
            for (int next = 1; next <= N; next ++) {
                if (matrix[curt][next] == 1) taller ++;
                if (matrix[next][curt] == 1) smaller ++;
            }
            if ((taller + smaller) == (N - 1)) answer ++;
        }
        System.out.println(answer);
    }
}
