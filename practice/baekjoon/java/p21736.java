
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class p21736 {

    private static class Element {

        private final int y, x;

        Element(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int[] DY = { -1, 0, 1, 0 }, DX = { 0, 1, 0, -1 };

    private static StringTokenizer st;

    private static int N, M, SY, SX, answer;

    private static boolean[][] visited;

    private static char[][] field;

    private static void bfs() {

        Queue<Element> queue = new ArrayDeque<>();

        queue.add(new Element(SY, SX));
        visited[SY][SX] = true;


        while (!queue.isEmpty()) {
            Element curt = queue.poll();

            for (int d = 0; d < 4; d ++) {
                Element next = new Element(curt.y + DY[d], curt.x + DX[d]);

                if (next.y >= N || next.y < 0 || next.x >= M || next.x < 0) continue;
                if (visited[next.y][next.x]) continue;
                if (field[next.y][next.x] == 'X') continue;

                visited[next.y][next.x] = true;

                if (field[next.y][next.x] == 'P') {
                    answer ++;
                }

                queue.add(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new char[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y ++) {
            char[] line = br.readLine().toCharArray();

            for (int x = 0; x < M; x ++) {
                field[y][x] = line[x];

                if (field[y][x] == 'I') {
                    SY = y; SX = x;
                }
            }
        }

        bfs();

        bw.write((answer > 0 ? answer : "TT") + "\n");
        bw.flush();
    }

}
