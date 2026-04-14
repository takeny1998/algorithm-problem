package algospot.java.BOARDCOVER;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, H, W;

    private static boolean[][] visited;

    private static final int[] D1Y = { +1, +0, +1, +1 }, D1X = { +0, +1, -1, +0 },
                               D2Y = { +0, +1, +1, +1 }, D2X = { +1, +1, +0, +1 };

    private static int dfs() {
        int y = -1, x = -1;

        for (int cy = 0; cy < H; cy ++) {
            for (int cx = 0; cx < W; cx ++) {
                if (!visited[cy][cx]) {
                    y = cy; x = cx;
                    break;
                }
            }
            if (y != -1) break;
        }

        if (y == -1 || x == -1) return 1;

        int ret = 0;

        for (int d = 0; d < 4; d ++) {
            int n1y = y + D1Y[d], n1x = x + D1X[d];
            int n2y = y + D2Y[d], n2x = x + D2X[d];

            if (n1y >= H || n2y >= H || n1y < 0 || n2y < 0 || n1x >= W || n2x >= W || n1x < 0 || n2x < 0) continue;
            if (visited[y][x] || visited[n1y][n1x] || visited[n2y][n2x]) continue;
            visited[y][x] = visited[n1y][n1x] = visited[n2y][n2x] = true;

            ret += dfs();

            visited[y][x] = visited[n1y][n1x] = visited[n2y][n2x] = false;
        }

        return ret;
    }
    
    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c ++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            visited = new boolean[H][W];

            for (int y = 0; y < H; y ++) {
                char[] line = br.readLine().toCharArray();
                for (int x = 0; x < W; x ++) {
                    if (line[x] == '#') {
                        visited[y][x] = true;
                    }
                }
            }

            bw.write(dfs() + "\n");
            
        }
        bw.flush();
    }
}
