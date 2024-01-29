import java.io.*;
import java.util.*;

public class P1389 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N, M;
    private static int[] minDist;
    private static boolean[][] relationship;
    private static boolean[] visited;

    private static int bfs(int index) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int curt = queue.poll();
            int time = minDist[curt];

            for (int next = 1; next <= N; next ++) {
                if (!relationship[curt][next]) continue;
                if (visited[next]) continue;

                visited[next] = true;
                minDist[next] = time + 1;
                queue.add(next);

            }
        }

        int kevinBacon = 0;

        for (int dist : minDist) {
            kevinBacon += dist;
        }

        return kevinBacon;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relationship = new boolean[N + 1][N + 1];

        int A, B;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            relationship[A][B] = true;
            relationship[B][A] = true;
        }

        int min = Integer.MAX_VALUE;
        int result = 1;

        for (int i = 1; i <= N; i ++) {
            visited = new boolean[N + 1];
            minDist = new int[N + 1];

            int kevinBacon = bfs(i);

            if (kevinBacon < min) {
                min = kevinBacon;
                result = i;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
