
import java.io.*;
import java.util.*;

public class p2667 {

    private static class Element {

        private int y, x;

        Element(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final Queue<Element> queue = new ArrayDeque<>();

    private static final List<Integer> answers = new ArrayList<>();

    private static final int[] DY = { -1, 0, 1, 0 }, DX = { 0, 1, 0, -1 };

    private static int N;

    private static int[][] matrix;

    private static boolean[][] visited;

    private static void bfs(int sy, int sx) {

        queue.add(new Element(sy, sx));
        visited[sy][sx] = true;

        int answer = 1;

        while (!queue.isEmpty()) {
            Element curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                Element next = new Element(curt.y + DY[d], curt.x + DX[d]);

                if (next.y < 0 || next.y >= N || next.x < 0 || next.x >= N) continue;
                if (visited[next.y][next.x]) continue;
                if (matrix[next.y][next.x] == 0) continue;

                visited[next.y][next.x] = true;
                answer ++;
                queue.add(next);
            }
        }

        answers.add(answer);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        visited = new boolean[N][N];

        for (int y = 0; y < N; y ++) {
            char[] chars = br.readLine().toCharArray();

            for (int x = 0; x < N; x ++) {
                matrix[y][x] = Integer.parseInt(chars[x] + "");
            }
        }

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < N; x ++) {
                if (visited[y][x] || matrix[y][x] == 0) continue;
                bfs(y, x);
            }
        }

        Collections.sort(answers);

        bw.write(answers.size() + "\n");

        for (int answer : answers) {
            bw.write(answer + "\n");
        }
        bw.flush();
    }

}
