
import java.io.*;
import java.util.*;

public class p15666 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;
    private static int[] numbers, depths;

    private static void dfs(int depth, int start) {
        if (depth == M) {
            for (int result : depths) {
                sb.append(result).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = 0;

        for (int next = start; next < N; next ++) {
            if (prev == numbers[next]) continue;

            depths[depth] = numbers[next];
            prev = numbers[next];
            dfs(depth + 1, next);
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        depths = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        dfs(0, 0);
        System.out.print(sb.toString());
    }
}
