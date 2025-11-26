
import java.io.*;
import java.util.StringTokenizer;

public class p1182 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N, S, result;
    private static int[] numbers;

    private static void dfs(int index, int sum) {
        if (index == N) {
            if (sum == S) result ++;
            return;
        }

        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        if (S == 0) result --;

        dfs(0, 0);

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
