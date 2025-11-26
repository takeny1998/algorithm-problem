
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1965 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int n;

    private static int[] boxes, dp;

    public static void main(String[] args) throws Exception {

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        boxes = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i ++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;

        int answer = 0;

        for (int i = 1; i < n; i ++) {

            int leftMax = 0;

            for (int r = 0; r < i; r ++) {
                if (boxes[r] >= boxes[i]) continue;
                leftMax = Math.max(leftMax, dp[r]);
            }

            dp[i] = leftMax + 1;
            answer = Math.max(answer, dp[i]);
        }

        bw.write(answer + "\n");
        bw.flush();

    }

}
