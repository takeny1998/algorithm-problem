
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class p1092 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static Integer[] C, B;

    private static boolean[] visited;

    private static int N, M;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        C = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new Integer[M];
        visited = new boolean[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i ++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(C, Collections.reverseOrder());
        Arrays.sort(B, Collections.reverseOrder());

        int answer = 0;

        int visitedCnt = 0;

        if (B[0] > C[0]) {
            bw.write(-1 + "\n");
            bw.flush();
            return;
        }

        int[] pos = new int[N];


        while (visitedCnt < M) {
            answer ++;

            for (int i = 0; i < N; i ++) {

                int r;

                for (r = pos[i]; r < M; r ++) {
                    if (B[r] > C[i]) continue;
                    if (visited[r]) continue;
                    visited[r] = true;
                    visitedCnt ++;
                    break;
                }


                pos[i] = r;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }

}
