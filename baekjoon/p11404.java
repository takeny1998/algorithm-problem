import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11404 {
    static int[][] matrix;
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        matrix = new int[N + 1][N + 1];

        for (int i = 0; i < M; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (matrix[A][B] == 0 || C < matrix[A][B]) {
                matrix[A][B] = C;
            }
        }

        for (int stopover = 1; stopover <= N; stopover ++) {
            for (int from = 1; from <= N; from ++) {
                for (int to = 1; to <= N; to ++) {

                    if (from != to && matrix[from][stopover] != 0 && matrix[stopover][to] != 0) {
                        int passDist = matrix[from][stopover] + matrix[stopover][to];
                        if (matrix[from][to] == 0 || passDist < matrix[from][to]) {

                            matrix[from][to] = passDist;
                        }
                    }
                }
            }
        }
        for (int y = 1; y <= N; y ++) {
            for (int x = 1; x <= N; x ++) {
                System.out.print(matrix[y][x] + " ");
            }
            System.out.println();
        }
    }
}
