
import java.io.*;
import java.util.*;


public class p2531 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int[] arr, count;

    private static int N, d, k, c, answer = 0;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[N + k - 1];
        count = new int[d + 1];

        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N; i < N + k - 1; i ++) {
            arr[i] = arr[i - N];
        }

        count[c] = 1;
        answer = 1;

        for (int i = 0; i < k; i ++) {
            count[arr[i]] ++;

            if (count[arr[i]] == 1) answer ++;
        }

        int cnt = answer;

        for (int i = 1; i < N; i ++) {
            count[arr[i - 1]] --;

            if (count[arr[i - 1]] == 0) cnt --;

            count[arr[i + (k - 1)]] ++;
            if (count[arr[i + (k - 1)]] == 1) cnt ++;

            answer = Math.max(answer, cnt);
        }

        bw.write(answer + "\n");
        bw.flush();
    }

}
