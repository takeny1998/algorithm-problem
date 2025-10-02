package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p18111 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M, B, ansTime = Integer.MAX_VALUE, ansTh;

    private static int[][] field;


    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        field = new int[N][M];

        for (int y = 0; y < N; y ++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < M; x ++) {
                field[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int th = 0; th < 257; th ++) {
            int takes = 0, deploys = 0;

            for (int y = 0; y < N; y ++) {
                for (int x = 0; x < M; x ++) {
                    if (field[y][x] > th) {
                        takes += field[y][x] - th;
                    } else {
                        deploys += th - field[y][x];
                    }
                }
            }

            if ((takes + B) < deploys) continue;

            int time = (takes * 2) + deploys;

            if (time <= ansTime) {
                ansTime = time;
                ansTh = th;
            }

        }

        bw.write(ansTime + " " + ansTh + "\n");
        bw.flush();
    }
}
