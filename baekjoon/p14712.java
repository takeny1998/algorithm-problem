import java.io.*;
import java.util.StringTokenizer;

public class p14712 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N, M, result;
    private static int[][] matrix;

    private static void dfs(int index) {
        int n = index / M + 1;
        int m = index % M + 1;

        if (index >= (N * M)) {
            result ++;
            return;
        }

        // 넴모를 놓을 수 있는 경우
        if(canPut(n, m)) {
            matrix[n][m] = 1;
            dfs(index + 1);
            matrix[n][m] = 0;
        }
        // 넴모를 안 놓는 경우
        dfs(index + 1);
    }

    private static boolean canPut(int n, int m) {
        return matrix[n - 1][m - 1] == 0 || matrix[n - 1][m] == 0 || matrix[n][m - 1] == 0;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N + 1][M + 1];

        dfs(0);

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
