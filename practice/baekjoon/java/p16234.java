
import java.io.*;
import java.util.*;

public class p16234 {

    static class Element {

        private final int y, x;

        public Element(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, L, R;

    private static int[][] arr;

    private static final int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    public static int bfs() {
        final int[][] visited = new int[N][N];
        final Queue<Element> queue = new ArrayDeque<>();
        int cnt = 1;

        for (int y = 0; y < N; y ++) {
            for (int x = 0; x < N; x ++) {
                if (visited[y][x] != 0) continue;

                queue.add(new Element(y, x));

                while (!queue.isEmpty()) {
                    final Element elm = queue.poll();

                    final int curt = arr[elm.y][elm.x];

                    for (int d = 0; d < 4; d ++) {
                        final int ny = elm.y + dy[d], nx = elm.x + dx[d];

                        if (ny >= N || ny < 0 || nx >= N || nx < 0) continue;
                        if (visited[ny][nx] != 0) continue;

                        final int next = arr[ny][nx];
                        final int diff = Math.abs(curt - next);

                        if (diff >= L && diff <= R) {
                            visited[ny][nx] = cnt;
                            queue.add(new Element(ny, nx));
                        }
                    }

                }

                cnt ++;
            }
        }

        int changed = 0;

        for (int i = 1; i <= cnt; i ++) {
            int sum = 0;
            int count = 0;

            for (int y = 0; y < N; y ++) {
                for (int x = 0; x < N; x ++) {
                    if (visited[y][x] != i) continue;
                    sum += arr[y][x];
                    count ++;
                }
            }

            if (count == 0) continue;

            int avg = sum / count;

            for (int y = 0; y < N; y ++) {
                for (int x = 0; x < N; x ++) {
                    if (visited[y][x] != i) continue;
                    arr[y][x] = avg;
                    changed ++;
                }
            }
        }

        return changed;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());

            for (int r = 0; r < N; r ++) {
                arr[i][r] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            final int changed = bfs();
            answer ++;

            if (changed == 0) break;
        }

        bw.write((answer - 1) + "\n");
        bw.flush();
    }

}
