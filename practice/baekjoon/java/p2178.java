
import java.io.*;
import java.util.*;

public class p2178 {

    private static class Element {

        int y, x, dist;

        Element(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static final int[] DY = { -1, 0, 1, 0 }, DX = { 0, 1, 0, -1 };

    private static int[][] matrix, dists;

    private static int N, M;

    private static void bfs() {

        Queue<Element> queue = new ArrayDeque<>();

        queue.add(new Element(0, 0, 1));
        dists[0][0] = 1;

        while(!queue.isEmpty()) {

            Element curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                Element next = new Element(curt.y + DY[d], curt.x + DX[d], curt.dist + 1);

                if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue;
                if (dists[next.y][next.x] != 0 || matrix[next.y][next.x] == 0) continue;

                dists[next.y][next.x] = next.dist;
                queue.add(next);
            }
        }

    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        dists = new int[N][M];

        for (int n = 0; n < N; n ++) {
            char[] chars = br.readLine().toCharArray();

            for (int m = 0; m < M; m ++) {
                matrix[n][m] = Integer.parseInt(chars[m] + "");
            }
        }

        bfs();

        bw.write(dists[N - 1][M - 1] + "\n");
        bw.flush();
    }

}
