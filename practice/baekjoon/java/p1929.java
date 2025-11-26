
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1929 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int M, N;
    private static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= N; i ++) {
            if (!isPrime[i]) continue;

            for (int j = i * i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = M; i <= N; i ++) {
            if (isPrime[i]) bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }
}
