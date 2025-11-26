
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1106 {

    private static class Node {

        private final int cost, value;

        Node(int cost, int value) {
            this.cost = cost;
            this.value = value;
        }

    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, N;

    private static int[] dp;

    private static Node[] nodes;

    private static int MAX = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        dp = new int[2001];

        Arrays.fill(dp, MAX);

        N = Integer.parseInt(st.nextToken());
        nodes = new Node[N];

        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp[0] = 0;

        for (int c = 1; c <= 2000; c ++) {
            for (int i = 0; i < N; i ++) {
                Node node = nodes[i];

                if (node.value > c) continue;
                dp[c] = Math.min(dp[c], dp[c - node.value] + node.cost);
            }
        }
        int answer = MAX;

        for (int c = C; c <= 2000; c ++) {
            answer = Math.min(answer, dp[c]);
        }
        bw.write(answer + "\n");
        bw.flush();
    }

}
