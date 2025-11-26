
import java.io.*;
import java.util.*;

public class p1027 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static long[] arr;

    private static int N;

    private static double getDelta(int i, int r) {
        return (double) (arr[i] - arr[r]) / (i - r);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        for (int curt = 0; curt < N; curt ++) {
            int count = 0;

            double leftMin = Double.MAX_VALUE, rightMax = -Double.MAX_VALUE;

            for (int left = (curt - 1); left >= 0; left --) {
                double delta = getDelta(left, curt);
                if (delta >= leftMin) continue;
                leftMin = delta;
                count ++;
            }

            for (int right = curt + 1; right < N; right ++) {
                double delta = getDelta(curt, right);

                if (delta <= rightMax) continue;
                rightMax = delta;
                count ++;
            }

            answer = Math.max(answer, count);
        }

        bw.write(answer + "\n");
        bw.flush();
    }

}
