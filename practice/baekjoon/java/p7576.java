
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7576 {
    private static int X, Y;
    private static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    private static int[][] field, time;
    private static boolean[][] visited;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static class Coords {
        int y, x;
        Coords(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int bfs() {
        Queue<Coords> queue = new LinkedList<Coords>();

        for (int y = 0; y < Y; y ++) {
            for (int x = 0; x < X; x ++) {
                if(field[y][x] == 1) {
                    queue.add(new Coords(y, x));
                    time[y][x] = 0;
                    visited[y][x] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Coords curt = queue.poll();
            int sec = time[curt.y][curt.x] + 1;

            for (int d = 0; d < 4; d ++) {
                int ny = curt.y + dy[d], nx = curt.x + dx[d];

                if (0 > ny || ny >= Y || 0 > nx || nx >= X) continue;
                if (visited[ny][nx]) continue;
                if (field[ny][nx] == -1) continue;

                visited[ny][nx] = true;
                time[ny][nx] = sec;

                queue.add(new Coords(ny, nx));
            }
        }

        int max = -1;

        for (int y = 0; y < Y; y ++) {
            for (int x = 0; x < X; x ++) {
                if (field[y][x] == 0 && time[y][x] == -1) {
                    return -1;
                }

                if(field[y][x] != -1 && time[y][x] != -1) {
                    max = Math.max(max, time[y][x]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        field = new int[Y + 1][X + 1];
        time = new int[Y + 1][X + 1];
        visited = new boolean[Y + 1][X + 1];

        for (int[] line : time) {
            Arrays.fill(line, -1);
        }

        for (int y = 0; y < Y; y ++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x ++) {
                field[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }
}
