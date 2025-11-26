
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p22945 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] x;

    private static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        x = new int[N];

        for (int i = 0; i < N; i ++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;

        int answer = 0;

        while (left < right) {
            final int score = (right - left - 1) * Math.min(x[left], x[right]);

            answer = Math.max(answer, score);

            if (x[left] <= x[right]) left ++;
            else right --;
        }

        bw.write(answer + "\n");
        bw.flush();
    }

}
