
import java.util.*;
import java.io.*;

public class p15654 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int[] numbers, results;
    private static int N, M;
    private static boolean[] isVisited;

    private static void dfs(int depth) {
        if (depth == M){
            for (int i = 0; i < M; i++){
                sb.append(results[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                results[depth] = numbers[i];
                dfs(depth + 1);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        results = new int[N];
        isVisited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        dfs(0);
        System.out.print(sb);
    }
}
