
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1005 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int T, N, K, W, X, Y, answer;

    private static int[] D, dp;

    private static boolean[] visisted;

    private static List<List<Integer>> E;

    private static int dfs(int curt) {
        if (visisted[curt]) return dp[curt];

        int prevMax = 0;

        for (int prev : E.get(curt)) {
            prevMax = Math.max(prevMax, dfs(prev));
        }

        dp[curt] = D[curt] + prevMax;
        visisted[curt] = true;

        return dp[curt];
    }

    public static void main(String[] args) throws Exception {

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            answer = 0;

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            D = new int[N + 1];

            dp = new int[N + 1];
            visisted = new boolean[N + 1];

            E = new ArrayList<>();

            for (int i = 0; i <= N; i ++) {
                E.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i ++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            for (int k = 0; k < K; k ++) {
                st = new StringTokenizer(br.readLine());

                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());

                // 역방향 그래프
                E.get(Y).add(X);
            }

            W = Integer.parseInt(br.readLine());
            dfs(W);

            bw.write(dp[W] + "\n");
        }

        bw.flush();
    }

}
