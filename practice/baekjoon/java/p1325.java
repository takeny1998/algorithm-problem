
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p1325 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N, M;
    private static ArrayList<Integer>[] graph;
    private static boolean[] isVisited;
    private static int[] depths;

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        isVisited[start] = true;

        while (!queue.isEmpty()) {
            int curt = queue.poll();

            for (int next : graph[curt]) {
                if (isVisited[next]) continue;
                isVisited[next] = true;
                depths[next] ++;
                queue.add(next);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        depths = new int[N + 1];

        for (int i = 0; i <= N; i ++) {
            graph[i] = new ArrayList<>();
        }

        int A, B;

        for (int i = 0; i < M; i ++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            // A가 B를 신뢰한다는 뜻?
            // - 반대로 말하면 B는 A를 신뢰하지 않는다.
            // 즉, 단방향 그래프이다.
            graph[A].add(B);
        }

        for (int i = 1; i <= N; i ++) {
            isVisited = new boolean[N + 1];
            bfs(i);
        }

        int max = depths[1];
        for (int i = 1; i <= N; i ++) {
            max = Math.max(max, depths[i]);
        }

        for (int i = 1; i <= N; i ++) {
            if (depths[i] == max)
                sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }
}
