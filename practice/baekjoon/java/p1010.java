
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1010 {
    static int[][] pascal = new int[31][31];
    static int N, M, T;

    static void make_pascal() {
        pascal[0][0] = 1;

        for (int y = 1; y <= 30; y ++) {
            pascal[y][0] = 1;
            for (int x = 1; x <= y; x ++) {
                pascal[y][x] = pascal[y - 1][x] + pascal[y - 1][x - 1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        make_pascal();
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            sb.append(pascal[M][N] + "\n");
        }

        System.out.println(sb);
    }
}
