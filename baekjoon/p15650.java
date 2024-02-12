import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15650 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N, M;
    private static int[] numbers;

    private static void dfs(int index, int depth) {
        if (depth == M) {
            for (int number : numbers)
                sb.append(number).append(" ");
            sb.append("\n");
            return;
        }

        for (int curt = index; curt <= N; curt ++) {
            numbers[depth] = curt;
            dfs(curt + 1, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[M];
        dfs(1, 0);

        System.out.print(sb);
    }
}
