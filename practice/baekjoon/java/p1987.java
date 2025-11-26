
import java.io.*;
import java.util.StringTokenizer;

public class p1987 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int R, C, max = 1;
    private static int[][] matrix;
    private static boolean[] alpha = new boolean[26];
    private static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    private static void dfs(int y, int x, int depth) {
        if (alpha[matrix[y][x]]) {
            max = Math.max(max, depth);
            return;
        }

        alpha[matrix[y][x]] = true;
        for (int d = 0; d < 4; d ++) {
            int ny = y + dy[d], nx = x + dx[d];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;

            dfs(ny, nx, depth + 1);
        }
        alpha[matrix[y][x]] = false;
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];

        for (int i = 0; i < R; i ++) {
            String str = br.readLine();
            for (int r = 0; r < C; r ++) {
                matrix[i][r] = str.charAt(r) - 'A';
            }
        }

        dfs(0, 0, 0);

        bw.write(max + "");
        bw.flush();
        bw.close();
    }
}
