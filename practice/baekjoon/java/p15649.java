package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p15649 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int N, M;

    private static int[] answer;

    private static boolean[] visisted;

    private static void dfs(int i, int r) throws Exception {
        if (r == M) {
            for (int k = 0; k < r; k ++) {
                bw.write(answer[k] + " ");
            }
            if (r > 0) bw.write("\n");
            return;
        }

        for (int n = 0; n < N; n ++) {
            if (visisted[n]) continue;

            visisted[n] = true;
            answer[r ++] = n + 1;
            dfs(n, r);
            r --;
            visisted[n] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());
        answer = new int[M];

        visisted = new boolean[N];
        dfs(0, 0);
        bw.flush();
    }
}
