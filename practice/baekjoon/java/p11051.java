
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11051 {
    static int[][] pascal;
    static int N, K;

    static void make_pascal() {
        for (int y = 0; y <= N; y ++) {
            for (int x = 0; x <= y; x ++) {
                if (y == 0 || x == 0) pascal[y][x] = 1;
                else pascal[y][x] = (pascal[y - 1][x] + pascal[y - 1][x - 1]) % 10007;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pascal = new int[N + 1][N + 1];

        make_pascal();
        System.out.println(pascal[N][K]);
    }
}
