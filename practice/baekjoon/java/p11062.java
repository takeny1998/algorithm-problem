
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11062 {
    static int T, N, answer;
    static int[] card;
    static int[][][] dp;

    static void recursion(int left, int right, int score, int myTurn) {
        if (left > right) {
            answer = Math.min(answer, score);
            return;
        }

        if (dp[left][right][myTurn] == 0) {
            if (card[left] > card[right]) {
                if (myTurn == 1) score += card[left];
                left ++;
            }
            else {
                if (myTurn == 1) score += card[right];
                right --;
            }

            if (left < N && right >= 0)
                dp[left][right][myTurn] = score;
            recursion(left, right, score, Math.abs(myTurn - 1));
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t ++) {
            answer = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            card = new int[N + 1];
            dp = new int[N + 1][N + 1][2];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i ++) {
                card[i] = Integer.parseInt(st.nextToken());
            }
            recursion(0, N - 1, 0, 1);

            System.out.println(answer);
        }
    }
}
