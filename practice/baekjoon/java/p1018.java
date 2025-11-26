
import java.io.*;
import java.util.*;

public class p1018 {

    private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw =
            new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int answer = Integer.MAX_VALUE;

    private static char[][] matrix;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][];

        for (int n = 0; n < N; n ++) {
            matrix[n] = br.readLine().toCharArray();
        }

        for (int sy = 0; sy < (N - 8) + 1; sy ++) {

            for (int sx = 0; sx < (M - 8) + 1; sx ++) {
                int w = 0, b = 0;

                for (int y = sy; y < (sy + 8); y ++) {
                    for (int x = sx; x < (sx + 8); x ++) {

                        if ((y + 1) % 2 != 0) {
                            if ((x + 1) % 2 != 0 && matrix[y][x] != 'W') w ++;
                            if ((x + 1) % 2 == 0 && matrix[y][x] != 'B') w ++;

                            if ((x + 1) % 2 != 0 && matrix[y][x] != 'B') b ++;
                            if ((x + 1) % 2 == 0 && matrix[y][x] != 'W') b ++;
                        } else {
                            if ((x + 1) % 2 != 0 && matrix[y][x] != 'B') w ++;
                            if ((x + 1) % 2 == 0 && matrix[y][x] != 'W') w ++;

                            if ((x + 1) % 2 != 0 && matrix[y][x] != 'W') b ++;
                            if ((x + 1) % 2 == 0 && matrix[y][x] != 'B') b ++;
                        }
                    }
                }
                answer = Math.min(answer, Math.min(w, b));
            }

        }

        bw.write(answer + "\n");
        bw.flush();
    }


}
