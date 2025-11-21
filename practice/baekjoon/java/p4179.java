package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p4179 {

    private static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int[][] move, fire;

    private static boolean[][] isVisited;

    private static Queue<Pair> queue = new LinkedList<>();
    private static Queue<Pair> fireQueue = new LinkedList<>();

    private static int R, C;

    private static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    private static void bfs() {

        while (!queue.isEmpty()) {

            Pair curt = queue.poll();

            for (int d = 0; d < 4; d ++) {

                int ny = curt.y + dy[d];
                int nx = curt.x + dx[d];


                if (ny < 0 || ny >= R || nx < 0 || nx >= C)
                    continue;

                int nextValue = move[curt.y][curt.x] + 1;
                if (move[ny][nx] != 0 || nextValue >= fire[ny][nx]) continue;

                move[ny][nx] = nextValue;
                queue.add(new Pair(ny, nx));

            }
        }

    }

    private static void fireBfs() {

        while (!fireQueue.isEmpty()) {

            Pair curt = fireQueue.poll();

            for (int d = 0; d < 4; d ++) {

                int ny = curt.y + dy[d];
                int nx = curt.x + dx[d];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C)
                    continue;

                if (fire[ny][nx] != Integer.MAX_VALUE) continue;
                int nextValue = fire[curt.y][curt.x] + 1;
                fire[ny][nx] = nextValue;

                fireQueue.add(new Pair(ny, nx));

            }
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        move = new int[R][C];
        fire = new int[R][C];

        for (int[] line : fire) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        for (int r = 0; r < R; r ++) {
            String[] tokens = br.readLine().split("");

            for (int c = 0; c < C; c ++) {
                String str = tokens[c];

                if (str.equals("#")) {
                    move[r][c] = -1;
                    fire[r][c] = -1;
                } else if (str.equals("J")) {
                    move[r][c] = 1;
                    queue.add(new Pair(r, c));
                } else if (str.equals("F")) {
                    fire[r][c] = 1;
                    fireQueue.add(new Pair(r, c));
                }
            }
        }
        fireBfs();

        for (int[] line : fire) {
            System.out.println(Arrays.toString(line));
        }

        bfs();

        for (int[] line : move) {
            System.out.println(Arrays.toString(line));
        }

        int min = Integer.MAX_VALUE;

        for (int r = 0; r < R; r ++) {
            if (r == 0 || r == R - 1) {

                for (int c = 0; c < C; c ++) {
                    if (move[r][c] > 0) {
                        min = Math.min(min, move[r][c]);
                    }
                }

            } else {
                if (move[r][0] > 0)
                    min = Math.min(min, move[r][0]);
                if (move[r][C - 1] > 0)
                    min = Math.min(min, move[r][C - 1]);
            }
        }

        if (min == Integer.MAX_VALUE) {
            bw.write("IMPOSSIBLE");
        } else {
            bw.write(min + "");
        }

        bw.flush();
        bw.close();
    }

}
