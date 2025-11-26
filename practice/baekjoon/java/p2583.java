
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p2583 {
    static boolean[][] visited;
    static int Y, X, K, area;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static ArrayList<Integer> areas = new ArrayList<Integer>();


    static void bfs(int y, int x) {
        visited[y][x] = true;
        area ++;

        for (int d = 0; d < 4; d ++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (!((0 <= ny && ny < Y) && (0 <= nx && nx < X)))
                continue;
            if (!visited[ny][nx]) bfs(ny, nx);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[Y][X];

        for (int i = 0; i < K; i ++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());

            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            for (int y = ly; y < ry; y++)
                for (int x = lx; x < rx; x++) {
                    visited[y][x] = true;
                }
        }

        for (int y = 0; y < Y; y ++) {
            for (int x = 0; x < X; x ++) {
                if (!visited[y][x]) {
                    area = 0;
                    bfs(y, x);
                    areas.add(area);
                }
            }
        }

        Collections.sort(areas);
        System.out.println(areas.size());
        for (int area : areas) {
            System.out.print(area + " ");
        }
    }
}
