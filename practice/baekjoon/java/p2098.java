
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2098 {
    static int[][] dp, weight;
    static final int INF = Integer.MAX_VALUE;
    static int N, visitedAll, answer = INF;

    static void recursion(int curt, int visited) {
        if (visited == visitedAll) {
            if (weight[curt][1] == 0)
                return;
            answer = Math.min(answer, dp[curt][visited] + weight[curt][1]);
        }

        for (int next = 1; next <= N; next ++ ) {
            int nextVisited = visited | (1 << (next - 1));

            if (visited != nextVisited && weight[curt][next] != 0) {
                int curtVal = dp[curt][visited] + weight[curt][next];

                if (dp[next][nextVisited] > curtVal) {
                    dp[next][nextVisited] = curtVal;
                    recursion(next, nextVisited);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visitedAll = (1 << N) - 1;

        dp = new int[N + 1][visitedAll + 1];
        weight = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 1; r <= N; r ++) {
                weight[i][r] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i ++) {
            for (int j = 1; j <= visitedAll; j ++) {
                dp[i][j] = INF;
            }
        }

        dp[1][1] = 0;
        recursion(1, 1);

        System.out.println(answer);
    }
}
