
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p2167 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M, K;

    private static long[][] sum;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sum = new long[N][M + 1];

        for (int n = 0; n < N; n ++) {
            st = new StringTokenizer(br.readLine());

            for (int m = 1; m < M + 1; m ++) {
                sum[n][m] = Integer.parseInt(st.nextToken()) + sum[n][m - 1];
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k ++) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            long answer = 0;

            for (int n = (i - 1); n < x; n ++) {
                answer += sum[n][y] - sum[n][r - 1];
            }

            bw.write(answer + "\n");
        }

        bw.flush();

    }

}
