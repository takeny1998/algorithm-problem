package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class p10026 {

    static class Node {
        final int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[][] arr;

    private static int[][] areaA, areaB;

    private static int N, cntA = 0, cntB = 0;

    private static final int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    public static void bfsA(int sy, int sx) {

        final Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(sy, sx));
        areaA[sy][sx] = ++cntA;


        while (!queue.isEmpty()) {
            final Node node = queue.poll();

            for (int d = 0; d < 4; d ++) {
                int ny = node.y + dy[d], nx = node.x + dx[d];
                
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (areaA[ny][nx] != 0) continue;
                if (arr[ny][nx] != arr[node.y][node.x]) continue;
                
                areaA[ny][nx] = cntA;
                queue.add(new Node(ny, nx));
            }
        }
    }


    public static void bfsB(int sy, int sx) {

        final Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(sy, sx));
        areaB[sy][sx] = ++cntB;


        while (!queue.isEmpty()) {
            final Node node = queue.poll();

            for (int d = 0; d < 4; d ++) {
                int ny = node.y + dy[d], nx = node.x + dx[d];
                
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (areaB[ny][nx] != 0) continue;
               
                if ((arr[ny][nx] == arr[node.y][node.x]) 
                    || (arr[ny][nx] == 'G' && arr[node.y][node.x] == 'R') 
                    || (arr[ny][nx] == 'R' && arr[node.y][node.x] == 'G')) {

                    areaB[ny][nx] = cntB;
                    queue.add(new Node(ny, nx));
                }

            }
        }
    }

    public static void main (String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        areaA = new int[N][N];
        areaB = new int[N][N];

        for (int y = 0; y < N; y ++) {
            final String line = br.readLine();

            for (int x = 0; x < N; x ++) {
                arr[y][x] = line.charAt(x);
            }
        }

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < N; x ++) {
                if (areaA[y][x] != 0) continue;
                bfsA(y, x);
            }
        }

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < N; x ++) {
                if (areaB[y][x] != 0) continue;
                bfsB(y, x);
            }
        }

        bw.write(cntA + " " + cntB + "\n");
        bw.flush();
    }
}
