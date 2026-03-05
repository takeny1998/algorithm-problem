package p18404;

import java.io.*;
import java.util.*;

public class Main {

    private static class Element {
        private int y, x, dist;

        Element(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<Element> queue = new ArrayDeque<>();

    private static StringTokenizer st;

    private static int[][] minDist, enemies;

    private static boolean[][] visited;

    private static int[] DY = { -1, 1, -2, 2, -2, 2, -1, 1 }, 
                         DX = { -2, -2, -1, -1, 1, 1, 2, 2 };

    private static int N, M, sy, sx;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minDist = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        enemies = new int[M][2];
        
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        
        for (int m = 0; m < M; m ++) {
            st = new StringTokenizer(br.readLine());
            enemies[m][0] = Integer.parseInt(st.nextToken());
            enemies[m][1] = Integer.parseInt(st.nextToken());
        }

        queue.add(new Element(sy, sx, 0));
        visited[sy][sx] = true;
        minDist[sy][sx] = 0;

        while (!queue.isEmpty()) {
            Element curt = queue.poll();

            for (int d = 0; d < 8; d ++) {
                Element next = new Element(curt.y + DY[d], curt.x + DX[d], curt.dist + 1);

                if (next.y > N || next.y < 1 || next.x > N || next.x < 1) continue;
                if (visited[next.y][next.x]) continue;
                visited[next.y][next.x] = true;
                minDist[next.y][next.x] = next.dist;

                queue.add(next);
            }
        }

        for (int m = 0; m < M; m ++) {
            bw.write(minDist[enemies[m][0]][enemies[m][1]] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
