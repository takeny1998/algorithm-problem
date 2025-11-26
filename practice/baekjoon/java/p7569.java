
import java.io.*;
import java.util.*;

public class p7569 {

    private static class Element {

        private int z, y, x, w;

        Element(int z, int y, int x, int w) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.w = w;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] DY = { -1, 0, 1, 0, 0, 0 },
                               DX = { 0, 1, 0, -1, 0, 0 },
                               DZ = { 0, 0, 0, 0, 1, -1 };

    private static Queue<Element> queue = new ArrayDeque<>();

    private static StringTokenizer st;

    private static int[][][] tenser;

    private static int M, N, H;

    private static void bfs() {
        while(!queue.isEmpty()) {
            Element curt = queue.poll();

            for (int d = 0; d < 6; d ++) {
                Element next = new Element(
                    curt.z + DZ[d], curt.y + DY[d], curt.x + DX[d], curt.w + 1);

                if (next.z < 0 || next.z >= H || next.y < 0 || next.y >= N || next.x < 0 || next.x >= M) continue;
                if (tenser[next.z][next.y][next.x] != 0) continue;
                tenser[next.z][next.y][next.x] = next.w;
                queue.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tenser = new int[H][N][M];

        for (int h = 0; h < H; h ++) {
            for (int n = 0; n < N; n ++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m ++) {
                    tenser[h][n][m] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int h = 0; h < H; h ++) {
            for (int n = 0; n < N; n ++) {
                for (int m = 0; m < M; m ++) {
                    if (tenser[h][n][m] != 1) continue;
                    queue.add(new Element(h, n, m, 1));
                }
            }
        }

        bfs();
        int answer = 0;

        for (int h = 0; h < H; h ++) {
            for (int n = 0; n < N; n ++) {
                for (int m = 0; m < M; m ++) {
                    if (tenser[h][n][m] == 0) {
                        bw.write("-1\n"); bw.flush();
                        return;
                    }
                    answer = Math.max(answer, tenser[h][n][m]);
                }
            }
        }

        bw.write((answer - 1) + "\n");
        bw.flush();
    }

}
