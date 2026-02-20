import java.io.*;
import java.util.*;

public class p16724 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M, answer = 0;

    private static char[][] matrix;

    private static int[][] visited;

    private static void dfs(int cn, int cm) {
        visited[cn][cm] = -1;

        int nn = cn, nm = cm;

        switch (matrix[cn][cm]) {
            case 'U': nn --; break;
            case 'D': nn ++; break;
            case 'L': nm --; break;
            case 'R': nm ++;
        };

        if (nn < 0 || nn >= N || nm < 0 || nm >= M) return;
        
        if (visited[nn][nm] == 0) {
            dfs(nn, nm);
        } else if (visited[nn][nm] == -1) {
            answer ++;
        }
        
        visited[cn][cm] = 1;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new int[N][M];

        for (int n = 0; n < N; n ++) {
            matrix[n] = br.readLine().toCharArray();
        }

        for (int n = 0; n < N; n ++) {
            for (int m = 0; m < M; m ++) {
                if (visited[n][m] != 0) continue;
                dfs(n, m);
            }
        }
        bw.write(answer + "\n");
        bw.flush();
    }
}
